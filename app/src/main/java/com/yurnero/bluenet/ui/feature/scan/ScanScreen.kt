package com.yurnero.bluenet.ui.feature.scan

import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.yurnero.bluenet.ui.feature.scan.ScanContract.Intent

@Composable
fun ScanScreen(navController: NavHostController) {
    val viewModel: ScanViewModel = hiltViewModel()
    Button(onClick = { viewModel.dispatchIntent(Intent.ScanDevice) }) {
        Text(text = "Scan device")
    }
}