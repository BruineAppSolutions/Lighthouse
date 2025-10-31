package com.bruine.lighthouseprocessor

import com.bruine.lighthouse.ScreenType
import com.google.devtools.ksp.processing.KSPLogger
import com.google.devtools.ksp.symbol.*

class DestinationSymbolVisitor(private val logger: KSPLogger) {

    fun visit(function: KSFunctionDeclaration): DestinationEntry? {
        val annotation = function.annotations.firstOrNull {
            it.shortName.asString() == "Destination"
        } ?: return null

        // --- Route type ---
        val routeArg = annotation.arguments.firstOrNull { it.name?.asString() == "route" }?.value
        val routeType = (routeArg as? KSType)
            ?: (routeArg as? KSClassDeclaration)?.asType(emptyList())
            ?: (routeArg as? KSTypeReference)?.resolve()

        // --- Deep link path ---
        val deepLinkPath = annotation.arguments
            .firstOrNull { it.name?.asString() == "deepLinkPath" }
            ?.value as? String ?: ""

        // --- Extra deep links (Array<DeepLink>) ---
        val extraDeepLinksRaw = annotation.arguments
            .firstOrNull { it.name?.asString() == "extraDeepLinks" }
            ?.value as? List<*>

        val extraDeepLinks: Map<String, String> = buildMap {
            extraDeepLinksRaw?.forEach { linkAnnotation ->
                if (linkAnnotation is KSAnnotation) {
                    val path = linkAnnotation.arguments
                        .firstOrNull { it.name?.asString() == "path" }
                        ?.value as? String ?: ""

                    val schemeOverride = linkAnnotation.arguments
                        .firstOrNull { it.name?.asString() == "schemeOverride" }
                        ?.value as? String ?: ""

                    if (path.isNotBlank()) put(path, schemeOverride)
                }
            }
        }

        // --- ScreenType (enum) ---
        val screenType = annotation.getScreenTypeOrDefault()

        // --- Validate route ---
        val routeDecl = routeType?.declaration as? KSClassDeclaration
        if (routeDecl == null) {
            logger.warn("⚠️ Could not resolve route type for function '${function.simpleName.asString()}'.")
            return null
        }

        // Must be @Serializable
        val isSerializable = routeDecl.annotations.any {
            it.shortName.asString() == "Serializable"
        }

        if (!isSerializable) {
            logger.error(
                """
                🚨 Route class '${routeDecl.simpleName.asString()}' must be annotated with @Serializable.
                Example:
                    @Serializable
                    data class ${routeDecl.simpleName.asString()}(...)
                """.trimIndent(),
                routeDecl
            )
            return null
        }

        return DestinationEntry(
            packageName = function.packageName.asString(),
            functionName = function.simpleName.asString(),
            routeClassName = routeDecl.simpleName.asString(),
            routeType = routeType,
            deepLinkPath = deepLinkPath,
            extraDeepLinks = extraDeepLinks,
            screenType = screenType,
            declaration = function
        )
    }

    // --- Enum extraction helper ---
    private fun KSAnnotation.getScreenTypeOrDefault(): ScreenType {
        val argValue = arguments.firstOrNull { it.name?.asString() == "screenType" }?.value

        return when (argValue) {
            is KSName -> runCatching { enumValueOf<ScreenType>(argValue.asString()) }.getOrDefault(ScreenType.FULL)
            is KSDeclaration -> {
                val name = argValue.simpleName.asString()
                runCatching { enumValueOf<ScreenType>(name) }.getOrDefault(ScreenType.FULL)
            }
            is KSType -> {
                val name = argValue.declaration.simpleName.asString()
                runCatching { enumValueOf<ScreenType>(name) }.getOrDefault(ScreenType.FULL)
            }
            else -> ScreenType.FULL
        }
    }
}
