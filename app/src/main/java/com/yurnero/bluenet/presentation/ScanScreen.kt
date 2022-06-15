package com.yurnero.bluenet.presentation

import android.Manifest
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.yurnero.bluenet.presentation.scan.ScanContract.Intent
import com.yurnero.bluenet.presentation.scan.ScanViewModel
import com.yurnero.bluenet.util.ComposablePermission

@Composable
fun ScanScreen() {
    // TODO move to MainScreen
    val viewModel: ScanViewModel = hiltViewModel()
    ComposablePermission(
        permission = Manifest.permission.ACCESS_FINE_LOCATION,
        onDenied = { Text("Permission Denied.") },
        onNotGranted = { requestPermission ->
            Button(onClick = requestPermission) {
                Text("Request permission.")
            }
        },
        onGranted = {
            Button(onClick = { viewModel.dispatchIntent(Intent.ScanDevice) }) {
                Text(text = "Scan device")
            }
        })
}