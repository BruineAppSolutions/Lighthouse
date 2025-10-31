package com.bruine.lighthouse

import kotlin.reflect.KClass

@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.SOURCE)
annotation class Destination(
    val route: KClass<out Route>,
    val screenType: ScreenType = ScreenType.FULL,
    val deepLinkPath: String = "",
    val extraDeepLinks: Array<DeepLink> = []
)
