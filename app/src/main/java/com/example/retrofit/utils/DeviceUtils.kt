package com.example.retrofit.utils

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.location.LocationManager
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.launch
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

@Composable
fun rememberDeviceAction(
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

@SuppressLint("MissingPermission")
@Composable
fun rememberDeviceAction(
    onLocationReceived: (Double, Double) -> Unit
): () -> Unit {
    val context = LocalContext.current

    return rememberPermissionAction(
        permissions = listOf(
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION
        ),
        onGranted = {
            val locationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
            val location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)
                ?: locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER)

            if (location != null) {
                onLocationReceived(location.latitude, location.longitude)
            }
        }
    )
}
