package com.bruine.lighthouse.demo

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.bruine.lighthouse.Destination
import com.bruine.lighthouse.LocalNavController
import com.bruine.lighthouse.Route
import kotlinx.serialization.Serializable

@Serializable
data object HomeRoute : Route

@Composable
@Destination(route = HomeRoute::class)
fun HomeScreen(
    navController: NavController = LocalNavController.current
) {
    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Button(onClick = {
                navController.navigate(ScreenARoute(id = "FromHome"))
            }) {
                Text("Open Screen A (Type: Full)")
            }

            Button(onClick = {
                navController.navigate(ScreenBRoute)
            }) {
                Text("Open Screen B (Type: Sheet)")
            }
        }
    }
}