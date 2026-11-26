package com.example.retrofit.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.retrofit.R
import com.example.retrofit.ui.components.MessageDialog
import com.example.retrofit.ui.components.button.MainButton
import com.example.retrofit.ui.components.text.InputFieldText
import com.example.retrofit.ui.components.text.PrimaryText
import com.example.retrofit.ui.theme.RetrofitTheme
import com.example.retrofit.ui.theme.viewModel.AuthViewModel

@Composable
fun LoginScreen(
    viewModel: AuthViewModel?,
    onLoginSuccess: () -> Unit,
    modifier: Modifier = Modifier
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }

    LaunchedEffect(viewModel?.isSuccess) {
        if (viewModel?.isSuccess == true) {
            onLoginSuccess()
        }
    }

    MessageDialog(
        message = viewModel?.errorMessage,
        onDismissRequest = { viewModel?.clearError() }
    )

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5F5))
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 20.dp),
        horizontalAlignment = Alignment.Start
    ) {
        Spacer(modifier = Modifier.height(35.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(modifier = Modifier.weight(1f).height(1.dp).background(Color(0xFFBDBDBD)))
            Spacer(modifier = Modifier.width(16.dp))
            Box(
                modifier = Modifier
                    .size(64.dp)
                    .border(1.dp, Color(0xFFBDBDBD), CircleShape),
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

        Spacer(modifier = Modifier.height(35.dp))
        PrimaryText(
            text = "Hello !",
            modifier = Modifier.padding(start = 10.dp),
            color = Color(0xFF909090),
            fontSize = 30.sp,
            fontWeight = FontWeight.Normal,
            letterSpacing = 0.sp
        )
        PrimaryText(
            text = "WELCOME BACK",
            modifier = Modifier.padding(start = 10.dp)
        )

        Spacer(modifier = Modifier.height(35.dp))
        InputFieldText(
            value = email,
            label = "Email",
            onValueChange = { email = it },
            modifier = Modifier.testTag("FieldEmail")
        )

        Spacer(modifier = Modifier.height(20.dp))
        InputFieldText(
            value = password,
            label = "Password",
            onValueChange = { password = it },
            modifier = Modifier.testTag("FieldPassword"),
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                    Icon(
                        painter = painterResource(id = R.drawable.eye_2),
                        contentDescription = "Toggle password",
                        tint = Color(0xFF242424)
                    )
                }
            }
        )

        Spacer(modifier = Modifier.height(35.dp))
        MainButton(
            textInButton = "Log in",
            onClick = { viewModel?.login(email, password) },
            modifier = Modifier.fillMaxWidth()
                .testTag("ButtonLogin")
        )

    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
private fun LoginScreenPreview() {
    RetrofitTheme {
        LoginScreen(
            viewModel = null,
            onLoginSuccess = {}
        )
    }
}
