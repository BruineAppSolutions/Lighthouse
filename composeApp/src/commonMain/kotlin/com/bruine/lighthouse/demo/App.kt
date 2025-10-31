package com.bruine.lighthouse.demo

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import com.bruine.lighthouse.generated.LighthouseNavHost
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    MaterialTheme {
        LighthouseNavHost(
            startDestination = HomeRoute,
            baseUri = "demo://bruine.com"
        )
    }
}
