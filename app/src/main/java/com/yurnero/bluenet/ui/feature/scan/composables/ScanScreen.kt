package com.yurnero.bluenet.ui.feature.scan.composables

import androidx.compose.runtime.Composable
import androidx.lifecycle.LiveData
import com.yurnero.bluenet.ui.feature.scan.ScanContract

@Composable
fun ScanScreen(
    state: ScanContract.State,
    effect: LiveData<ScanContract.Effect>?,
    onIntent: (intent: ScanContract.Intent) -> Unit,
    onNavigationRequested: (navigationEffect: ScanContract.Effect.Navigation) -> Unit
) {

//    Button(onClick = { viewModel.dispatchIntent(Intent.ScanDevice) }) {
//        Text(text = "Scan device")
//    }
}