package com.bruine.lighthouseprocessor

import com.google.devtools.ksp.processing.Resolver
import com.google.devtools.ksp.processing.SymbolProcessor
import com.google.devtools.ksp.processing.SymbolProcessorEnvironment
import com.google.devtools.ksp.processing.SymbolProcessorProvider
import com.google.devtools.ksp.symbol.KSAnnotated
import com.google.devtools.ksp.symbol.KSFunctionDeclaration
import com.google.devtools.ksp.validate

class LighthouseProcessor(
    private val environment: SymbolProcessorEnvironment
) : SymbolProcessor {

    private val logger = environment.logger
    private val codeGenerator = environment.codeGenerator

    override fun process(resolver: Resolver): List<KSAnnotated> {
        val destinationFunctions = resolver.getSymbolsWithAnnotation("com.bruine.lighthouse.Destination")
            .filterIsInstance<KSFunctionDeclaration>()

        if (!destinationFunctions.iterator().hasNext()) return emptyList()

        val symbolVisitor = DestinationSymbolVisitor(logger)

        val entries = destinationFunctions.map {
            symbolVisitor.visit(it)
        }.filterNotNull().toList()

        NavGraphGenerator(codeGenerator).generateNavGraph(entries)
        NavHostGenerator(codeGenerator).generateNavHost()

        return destinationFunctions.filterNot { it.validate() }.toList()
    }
}

class LighthouseProcessorProvider : SymbolProcessorProvider {
    override fun create(environment: SymbolProcessorEnvironment): SymbolProcessor {
        return LighthouseProcessor(environment)
    }
}
