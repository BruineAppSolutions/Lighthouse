package com.bruine.lighthouseprocessor

import com.bruine.lighthouse.ScreenType
import com.google.devtools.ksp.symbol.KSFunctionDeclaration
import com.google.devtools.ksp.symbol.KSType

data class DestinationEntry(
    val packageName: String,
    val functionName: String,
    val routeClassName: String,
    val routeType: KSType?,
    val deepLinkPath: String,
    val extraDeepLinks: Map<String, String>,
    val screenType: ScreenType,
    val declaration: KSFunctionDeclaration
)
