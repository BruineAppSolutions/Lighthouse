package com.bruine.lighthouse.demo

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.bruine.lighthouse.DeepLink
import com.bruine.lighthouse.Destination
import com.bruine.lighthouse.LocalRouter
import com.bruine.lighthouse.Route
import com.bruine.lighthouse.Router
import kotlinx.serialization.Serializable

@Serializable
data class ScreenARoute(
    val id: String? = null
) : Route

@Composable
@Destination(
    route = ScreenARoute::class,
    deepLinkPath = "/screenA/{id}",
    extraDeepLinks = [
        DeepLink("/screena"),
        DeepLink("/screena/{id}", baseUriOverride = "demo2://bruine.com")
    ]
)
fun ScreenA(
    route: ScreenARoute,
    router: Router = LocalRouter.current
) {
    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column {
            Text("This is screen A = ${route.id}")

            Button(onClick = {
                router.navigate(ScreenBRoute)
            }) {
                Text("Open Screen B (Type: Sheet)")
            }
        }
    }
}
