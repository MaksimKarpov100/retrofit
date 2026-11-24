package com.example.retrofit.utils

import android.Manifest
import android.content.pm.PackageManager
import android.graphics.Bitmap
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.launch
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat

@Composable
fun rememberPermissionAction(
    permission: String,
    onGranted: () -> Unit
): () -> Unit {
    val context = LocalContext.current
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            onGranted()
        }
    }

    return {
        val isGranted = ContextCompat.checkSelfPermission(
            context,
            permission
        ) == PackageManager.PERMISSION_GRANTED

        if (isGranted) {
            onGranted()
        } else {
            launcher.launch(permission)
        }
    }
}

@Composable
fun rememberCameraAction(
    onPhotoTaken: (Bitmap) -> Unit
): () -> Unit {
    val cameraLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicturePreview()
    ) { bitmap ->
        if (bitmap != null) {
            onPhotoTaken(bitmap)
        }
    }

    return rememberPermissionAction(
        permission = Manifest.permission.CAMERA,
        onGranted = { cameraLauncher.launch() }
    )
}
