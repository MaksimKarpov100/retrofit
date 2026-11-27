package com.example.retrofit.utils

import android.Manifest
import android.graphics.Bitmap
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.launch
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.FileProvider
import java.io.File
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun rememberCameraAction(
    permission: String = Manifest.permission.CAMERA,
    onPhotoSaved: (File) -> Unit
): () -> Unit {

    val context = LocalContext.current
    val currentOnPhotoSaved by rememberUpdatedState(onPhotoSaved)
    var tempFilePath by rememberSaveable { mutableStateOf<String?>(null) }

    val cameraLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicture()
    ) {
        isSuccess ->
        val savedFile = tempFilePath?.let { File(it) }

        if (isSuccess && savedFile != null && savedFile.exists()) {
            currentOnPhotoSaved(savedFile)
        } else {
            savedFile?.delete()
        }

        tempFilePath = null
    }

    return rememberPermissionAction(

        permission = permission,
        onGranted = {
            val folder = File(context.filesDir, "avatars").apply {
                if (!exists()) mkdirs()
            }

            val dateStamp = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.US).format(Date())
            val newFile = File(folder, "photo_$dateStamp.jpg")
            tempFilePath = newFile.absolutePath

            val uri: Uri = FileProvider.getUriForFile(
                context, "${context.packageName}.fileprovider",
                newFile
            )
            cameraLauncher.launch(uri)
        }
    )
}
