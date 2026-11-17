package com.example.retrofit.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.retrofit.R
import com.example.retrofit.ui.theme.RetrofitTheme
import com.example.retrofit.ui.theme.White
import com.example.retrofit.ui.theme.viewModel.AuthViewModel

@Composable
fun LoginScreen(
    viewModel: AuthViewModel,
    onLoginSuccess: () -> Unit,
    modifier: Modifier = Modifier
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }

    LaunchedEffect(viewModel.isSuccess) {
        if (viewModel.isSuccess) {
            onLoginSuccess()
        }
    }
    viewModel.errorMessage?.let { error ->
        AlertDialog(
            onDismissRequest = { viewModel.clearError() },
            title = { Text("Ошибка авторизации") },
            text = { Text(error) },
            confirmButton = {
                TextButton(onClick = { viewModel.clearError() }) {
                    Text("ОК")
                }
            }
        )
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = White)
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 20.dp),
        horizontalAlignment = Alignment.Start
    ) {
        Spacer(modifier = Modifier.height(40.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(modifier = Modifier.weight(1f).height(1.dp).background(Color(0xFFBDBDBD)))
            Spacer(modifier = Modifier.width(16.dp))
            Box(
                modifier = Modifier
                    .size(64.dp)
                    .border(width = 1.dp, color = Color(0xFFBDBDBD), shape = CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.group),
                    contentDescription = "Logo",
                    tint = Color.Unspecified
                )
            }
            Spacer(modifier = Modifier.width(16.dp))
            Box(modifier = Modifier.weight(1f).height(1.dp).background(Color(0xFFBDBDBD)))
        }

        Spacer(modifier = Modifier.height(40.dp))
        Text(
            text = buildAnnotatedString {
                withStyle(
                    style = SpanStyle(
                        fontFamily = FontFamily(Font(R.font.merriweather)),
                        fontWeight = FontWeight.Normal,
                        fontSize = 34.sp,
                        color = Color(0xFF909090)
                    )
                ) {
                    append("Hello !\n")
                }
                withStyle(
                    style = SpanStyle(
                        fontFamily = FontFamily(Font(R.font.merriweather_bold)),
                        fontWeight = FontWeight.Bold,
                        fontSize = 26.sp,
                        color = Color(0xFF303030),
                        letterSpacing = 1.5.sp
                    )
                ) {
                    append("WELCOME BACK")
                }
            },
            style = TextStyle(lineHeight = 48.sp)
        )

        Spacer(modifier = Modifier.height(40.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(64.dp)
                .shadow(
                    elevation = 15.dp,
                    shape = RoundedCornerShape(4.dp),
                    ambientColor = Color(0xFF8A959E).copy(alpha = 0.2f),
                    spotColor = Color(0xFF8A959E).copy(alpha = 0.2f)
                )
                .background(White, RoundedCornerShape(4.dp))
        ) {
            TextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Email", fontSize = 12.sp, color = Color.Gray) },
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                ),
                modifier = Modifier.fillMaxSize()
            )
        }

        Spacer(modifier = Modifier.height(20.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(64.dp)
                .shadow(
                    elevation = 15.dp,
                    shape = RoundedCornerShape(4.dp),
                    ambientColor = Color(0xFF8A959E).copy(alpha = 0.2f),
                    spotColor = Color(0xFF8A959E).copy(alpha = 0.2f)
                )
                .background(White, RoundedCornerShape(4.dp))
        ) {
            TextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Password", fontSize = 12.sp, color = Color.Gray) },
                visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                trailingIcon = {
                    IconButton(onClick = { passwordVisible = !passwordVisible }) {
                        Icon(
                            painter = painterResource(id = R.drawable.eye_2),
                            contentDescription = "Toggle password",
                            tint = Color(0xFF303030)
                        )
                    }
                },
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                ),
                modifier = Modifier.fillMaxSize()
            )
        }

        Spacer(modifier = Modifier.height(40.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .shadow(
                    elevation = 10.dp,
                    shape = RoundedCornerShape(8.dp),
                    ambientColor = Color(0xFF303030).copy(alpha = 0.3f),
                    spotColor = Color(0xFF303030).copy(alpha = 0.3f)
                )
                .background(Color(0xFF242424), RoundedCornerShape(8.dp))
                .clickable {
                    viewModel.login(email, password)
                },
            contentAlignment = Alignment.Center
        ) {
            if (viewModel.isLoading) {
                CircularProgressIndicator(color = White, modifier = Modifier.size(24.dp))
            } else {
                Text(
                    text = "Log in",
                    color = White,
                    fontSize = 18.sp,
                    fontFamily = FontFamily(Font(R.font.merriweather_bold))
                )
            }
        }

        Spacer(modifier = Modifier.height(30.dp))
    }
}

@Preview()
@Composable
private fun LoginScreenPreview() {
    RetrofitTheme {
        LoginScreen(
            viewModel = viewModel(),
            onLoginSuccess = {}
        )
    }
}