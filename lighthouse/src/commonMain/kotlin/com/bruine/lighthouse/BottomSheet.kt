package com.bruine.lighthouse

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.window.DialogProperties
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavDeepLink
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.dialog

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
                onDismissRequest = { onDismissRequest() }
            ) {
                content(backStackEntry)
            }
        }
    )
}
