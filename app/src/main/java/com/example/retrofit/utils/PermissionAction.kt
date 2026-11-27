package com.example.retrofit.utils

import android.content.pm.PackageManager
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat

@Composable
fun rememberPermissionAction(
    permission: String,
    onGranted: () -> Unit
): () -> Unit {
    return rememberPermissionAction(
        permissions = listOf(permission),
        onGranted = onGranted
    )
}
@Composable
fun rememberPermissionAction(
    permissions: List<String>,
    onGranted: () -> Unit
): () -> Unit {
    val context = LocalContext.current
    val currentOnGranted by rememberUpdatedState(onGranted)

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestMultiplePermissions()
    ) { result ->
        if (result.values.all { it }) {
            currentOnGranted()
        }
    }

    return remember(permissions, context) {
        {
            val allGranted = permissions.all {
                ContextCompat.checkSelfPermission(context, it) == PackageManager.PERMISSION_GRANTED
            }
            if (allGranted) {
                currentOnGranted()
            } else {
                launcher.launch(permissions.toTypedArray())
            }
        }
    }
}