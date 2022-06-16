package com.yurnero.bluenet.ui.feature.main

import android.Manifest
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.yurnero.bluenet.ui.navigation.navigateToScanning
import com.yurnero.bluenet.util.ComposablePermission

@Composable
fun MainScreen(navController: NavHostController) {
    ComposablePermission(
        permission = Manifest.permission.ACCESS_FINE_LOCATION,
        onDenied = { Text("Permission Denied.") },
        onNotGranted = { requestPermission ->
            Button(onClick = requestPermission) {
                Text("Request permission.")
            }
        },
        onGranted = {
            Button(onClick = {
                navController.navigateToScanning()
            }) {
                Text("GOGOGO!!!")
            }
        })
}