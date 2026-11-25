package com.example.retrofit.utils

import android.Manifest
import android.graphics.Bitmap
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.launch
import androidx.compose.runtime.Composable

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
        permissions = listOf(Manifest.permission.CAMERA),
        onGranted = { cameraLauncher.launch() }
    )
}
