package com.bruine.lighthouse

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.window.DialogProperties
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavDeepLink
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.dialog

/**
 * The background of every [bottomSheet]. An app provides its own background colour here; without
 * it the sheet takes Material3's default (`surfaceContainerLow`), which is lilac unless the app
 * sets a Material colour scheme (seen in First Bites, #440).
 */
val LocalBottomSheetContainerColor = compositionLocalOf<Color?> { null }

@OptIn(ExperimentalMaterial3Api::class)
inline fun <reified T : Any> NavGraphBuilder.bottomSheet(
    crossinline onDismissRequest: () -> Unit = {},
    deepLinks: List<NavDeepLink> = emptyList(),
    noinline content: @Composable (NavBackStackEntry) -> Unit
) {
    dialog(
        route = T::class,
        deepLinks = deepLinks,
        dialogProperties = DialogProperties(
            dismissOnBackPress = true,
            dismissOnClickOutside = true,
            usePlatformDefaultWidth = true
        ),
        content = { backStackEntry ->
            ModalBottomSheet(
                modifier = Modifier.statusBarsPadding(),
                sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true),
                contentWindowInsets = { WindowInsets() },
                dragHandle = null,
                scrimColor = Color.Transparent,
                containerColor = LocalBottomSheetContainerColor.current ?: BottomSheetDefaults.ContainerColor,
                onDismissRequest = { onDismissRequest() }
            ) {
                content(backStackEntry)
            }
        }
    )
}
