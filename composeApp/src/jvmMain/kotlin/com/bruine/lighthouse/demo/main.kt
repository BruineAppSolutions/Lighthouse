package com.bruine.lighthouse.demo

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "Beacon",
    ) {
        App()
    }
}