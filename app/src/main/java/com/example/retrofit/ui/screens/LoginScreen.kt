package com.example.retrofit.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
import com.example.retrofit.R
import com.example.retrofit.ui.components.buttons.MainButton
import com.example.retrofit.ui.components.texts.ClickableText
import com.example.retrofit.ui.components.texts.InputFieldText
import com.example.retrofit.ui.theme.RetrofitTheme
import com.example.retrofit.ui.theme.White

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    onLoginClick: () -> Unit = {},
    onSignUpClick: () -> Unit = {},
    onForgotPasswordClick: () -> Unit = {}
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = White),
        horizontalAlignment = Alignment.Start
    ) {
        Spacer(modifier = Modifier.height(48.dp))
        Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 30.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .width(105.dp)
                        .height(1.dp)
                        .background(color = Color(0xFFBDBDBD))
                )
                Spacer(modifier = Modifier.weight(1f))
                Box(
                    modifier = Modifier
                        .size(64.dp)
                        .border(width = 1.dp, color = Color(0xFFBDBDBD), shape = CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.group),
                        contentDescription = "Cart",
                        tint = Color.Unspecified
                    )
                }
                Spacer(modifier = Modifier.weight(1f))
                Box(
                    modifier = Modifier
                        .width(105.dp)
                        .height(1.dp)
                        .background(color = Color(0xFFBDBDBD))
                )
            }
        }

        Spacer(modifier = Modifier.height(35.dp))
        Text(
            text = buildAnnotatedString {
                withStyle(style = SpanStyle(
                    fontFamily = FontFamily(Font(R.font.merriweather)),
                    fontWeight = FontWeight.Normal,
                    fontSize = 30.sp,
                    color = Color(0xFF909090)
                )) {
                    append("Hello !\n")
                }
                withStyle(style = SpanStyle(
                    fontFamily = FontFamily(Font(R.font.merriweather_bold)),
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp,
                    color = Color(0xFF303030),
                    letterSpacing = 1.2.sp
                )) {
                    append("WELCOME BACK")
                }
            },
            style = TextStyle(lineHeight = 45.sp),
            modifier = Modifier.padding(start = 30.dp)
        )

        Spacer(modifier = Modifier.height(25.dp))
        ElevatedCard(
            modifier = Modifier
                .width(345.dp)
                .heightIn(min = 437.dp)
                .padding(start = 4.dp),
            shape = RoundedCornerShape(4.dp),
            colors = CardDefaults.elevatedCardColors(
                containerColor = White
            ),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 12.dp
            )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 35.dp, bottom = 35.dp),
                horizontalAlignment = Alignment.Start
            ) {
                InputFieldText(
                    value = email,
                    onValueChange = { email = it },
                    label = "Email",
                    placeholder = "example@mail.com",
                    visualTransformation = VisualTransformation.None,
                    trailingIcon = null,
                    modifier = Modifier.padding(start = 26.dp)
                )

                Spacer(modifier = Modifier.height(30.dp))
                InputFieldText(
                    value = password,
                    onValueChange = { password = it },
                    label = "Password",
                    placeholder = "••••••••",
                    visualTransformation = if (passwordVisible)
                        VisualTransformation.None
                    else
                        PasswordVisualTransformation(),
                    trailingIcon = {
                        IconButton(
                            onClick = { passwordVisible = !passwordVisible },
                            modifier = Modifier.size(24.dp)
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.eye_2),
                                contentDescription = "Toggle password",
                                tint = Color.Unspecified
                            )
                        }
                    },
                    modifier = Modifier.padding(start = 26.dp)
                )

                Spacer(modifier = Modifier.height(35.dp))
                Box(modifier = Modifier.width(345.dp), contentAlignment = Alignment.Center) {
                    ClickableText(
                        text = "Forgot Password",
                        onClick = onForgotPasswordClick,
                        fontSize = 18
                    )
                }

                Spacer(modifier = Modifier.height(40.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 26.dp)
                ) {
                    MainButton(
                        enabled = true,
                        onClick = onLoginClick,
                        textInButton = "Log in",
                        textFontSize = 18
                    )
                }

                Spacer(modifier = Modifier.height(30.dp))
                Box(modifier = Modifier.width(345.dp), contentAlignment = Alignment.Center) {
                    ClickableText(
                        text = "SIGN UP",
                        onClick = onSignUpClick,
                        fontSize = 18
                    )
                }
            }
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
private fun LoginScreenPrev() {
    RetrofitTheme {
        LoginScreen()
    }
}
