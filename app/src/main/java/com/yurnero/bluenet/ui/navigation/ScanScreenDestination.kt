package com.yurnero.bluenet.ui.navigation

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.yurnero.bluenet.ui.feature.scan.ScanContract
import com.yurnero.bluenet.ui.feature.scan.ScanViewModel
import com.yurnero.bluenet.ui.feature.scan.composables.ScanScreen

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun ScanScreenDestination(navController: NavController) {
    val viewModel: ScanViewModel = hiltViewModel()
    ScanScreen(
        state = viewModel.viewState.value,
        effect = viewModel.effect,
        onIntent = { intent -> viewModel.dispatchIntent(intent) },
        onNavigationRequested = { navigationEffect ->
            if (navigationEffect is ScanContract.Effect.Navigation.ToConnection) {
                navController.navigateToConnection(navigationEffect.peripheralId)
            }
        }
    )
}