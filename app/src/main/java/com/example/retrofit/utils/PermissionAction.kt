package com.example.retrofit.utils

import android.content.pm.PackageManager
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
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
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestMultiplePermissions()
    ) { result ->
        if (result.values.any { it }) {
            onGranted()
        }
    }

    return {
        val isGranted = permissions.any {
            ContextCompat.checkSelfPermission(context, it) == PackageManager.PERMISSION_GRANTED
        }
        if (isGranted) {
            onGranted()
        } else {
            launcher.launch(permissions.toTypedArray())
        }
    }
}

