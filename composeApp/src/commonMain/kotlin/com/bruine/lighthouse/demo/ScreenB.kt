package com.bruine.lighthouse.demo

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.bruine.lighthouse.Destination
import com.bruine.lighthouse.Route
import com.bruine.lighthouse.ScreenType
import kotlinx.serialization.Serializable

@Serializable
data object ScreenBRoute : Route

@Composable
@Destination(route = ScreenBRoute::class, screenType = ScreenType.SHEET)
fun ScreenB() {
    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column {
            Text("This is screen B")
        }
    }
}
