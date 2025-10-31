package com.bruine.lighthouse

import androidx.compose.runtime.compositionLocalOf
import androidx.navigation.NavController

val LocalNavController = compositionLocalOf<NavController> {
    throw NullPointerException("No NavController provided")
}
