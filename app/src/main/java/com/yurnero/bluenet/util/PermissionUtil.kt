package com.yurnero.bluenet.util

import androidx.compose.runtime.Composable
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionRequired
import com.google.accompanist.permissions.rememberPermissionState

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun ComposablePermission(
    permission: String,
    onDenied: @Composable () -> Unit,
    onGranted: @Composable () -> Unit,
    onNotGranted: @Composable (requester: () -> Unit) -> Unit
) {

    val permissionState = rememberPermissionState(permission)
    PermissionRequired(
        permissionState = permissionState,
        permissionNotAvailableContent = {
            onDenied()
        }, permissionNotGrantedContent = {
            onNotGranted { permissionState.launchPermissionRequest() }
        }, content = {
            onGranted()
        }
    )
}