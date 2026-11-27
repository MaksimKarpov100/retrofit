package com.example.retrofit.ui.screen

import android.Manifest
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.retrofit.R
import com.example.retrofit.data.model.UserRecord
import com.example.retrofit.ui.components.AvatarPicker
import com.example.retrofit.ui.components.HeaderBar
import com.example.retrofit.ui.components.text.SecondaryText
import com.example.retrofit.ui.theme.RetrofitTheme
import com.example.retrofit.utils.rememberCameraAction
import java.io.File

@Composable
fun ProfileScreen(
    user: UserRecord?,
    onBackClick: () -> Unit = {},
    onLogoutClick: () -> Unit = {}
) {
    val context = LocalContext.current

    var avatarFile by remember {
        mutableStateOf(
            File(
                context.filesDir,
                "avatars"
            ).listFiles()?.maxByOrNull { it.lastModified() }
        )
    }

    val openCamera = rememberCameraAction(
        permission = Manifest.permission.CAMERA
    ) { savedFile ->
        avatarFile?.delete()
        avatarFile = savedFile
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .statusBarsPadding()
    ) {
        HeaderBar(
            title = "Profile",
            leftIconRes = R.drawable.search_icon,
            onLeftClick = onBackClick,
            rightIconRes = R.drawable.logout_icon,
            onRightClick = onLogoutClick
        )

        Spacer(modifier = Modifier.height(18.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AvatarPicker(
                avatar = avatarFile,
                onClick = openCamera
            )

            Spacer(modifier = Modifier.width(20.dp))
            Column {
                SecondaryText(
                    text = if (user != null) "${user.firstName} ${user.lastName}" else "",
                    color = Color(0xFF303030),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(5.dp))
                SecondaryText(
                    text = user?.email ?: "",
                    color = Color(0xFF808080),
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Normal
                )
            }
        }

        Spacer(modifier = Modifier.height(30.dp))
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 21.dp)
                .height(127.dp)
                .shadow(
                    elevation = 16.dp,
                    shape = RoundedCornerShape(8.dp),
                    ambientColor = Color(0xFF8A959E).copy(alpha = 0.20f),
                    spotColor = Color(0xFF8A959E).copy(alpha = 0.20f)
                )
                .background(Color.White, RoundedCornerShape(8.dp))
                .clip(RoundedCornerShape(8.dp))
                .padding(horizontal = 20.dp, vertical = 15.dp)
        ) {
            SecondaryText(
                text = "Shipping address",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF303030)
            )

            Spacer(modifier = Modifier.height(22.dp))
            SecondaryText(
                text =  "25 rue Robert Latouche, Nice, 06200, Côte D’azur, France",
                fontSize = 14.sp,
                fontWeight = FontWeight.Normal,
                color = Color(0xFF808080)
            )
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
private fun ProfileScreenPreview() {
    RetrofitTheme {
        ProfileScreen(
            user = UserRecord(
                "Bruno",
                "Smith",
                "bruno203@gmail.com",
                "",
                ""
            ),
        )
    }
}
