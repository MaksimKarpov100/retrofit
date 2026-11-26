package com.example.retrofit.utils

import android.content.pm.PackageManager
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import java.io.File
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

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
        if (result.values.all { it }) {
            onGranted()
        }
    }

    return {
        val allGranted = permissions.all {
            ContextCompat.checkSelfPermission(context, it) == PackageManager.PERMISSION_GRANTED
        }
        if (allGranted) {
            onGranted()
        } else {
            launcher.launch(permissions.toTypedArray())
        }
    }
}

@Composable
fun rememberCameraAction(
    permission: String,
    onPhotoSaved: (File) -> Unit
): () -> Unit {
    val context = LocalContext.current
    var tempFile by remember { mutableStateOf<File?>(null) }

    val cameraLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicture()
    ) { isSuccess ->
        val savedFile = tempFile
        if (isSuccess && savedFile != null) {
            onPhotoSaved(savedFile)
        } else {
            savedFile?.delete()
        }
    }

    return rememberPermissionAction(
        permission = permission,
        onGranted = {
            val folder = File(context.filesDir, "avatars")
            if (!folder.exists()) folder.mkdirs()

            val dateStamp = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())
            val newFile = File(folder, "photo_$dateStamp.jpg")
            tempFile = newFile

            val uri: Uri = FileProvider.getUriForFile(
                context,
                "${context.packageName}.fileprovider",
                newFile
            )
            cameraLauncher.launch(uri)
        }
    )
}
