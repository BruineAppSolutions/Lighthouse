package com.bruine.lighthouse

import androidx.compose.runtime.compositionLocalOf
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import kotlin.reflect.KClass

val LocalRouter = compositionLocalOf<Router> {
    throw IllegalStateException("No Router provided")
}

class Router(private val navController: NavController) {
    fun navigate(route: Route, navOptions: NavOptions? = null) {
        navController.navigate(route, navOptions)
    }

    fun popBackStack() : Boolean = navController.popBackStack()

    fun <T : Route> popBackStack(route: KClass<T>, inclusive: Boolean, saveState: Boolean = false): Boolean =
        navController.popBackStack(route, inclusive, saveState)
}
