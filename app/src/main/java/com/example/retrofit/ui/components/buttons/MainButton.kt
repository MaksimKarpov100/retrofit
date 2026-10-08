package com.example.retrofit.ui.components.buttons

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.retrofit.R
import com.example.retrofit.ui.theme.Black

@Composable
fun MainButton(
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    onClick: () -> Unit = {}, // Добавили onClick, чтобы кнопка работала
    textInButton: String,
    textFontSize: Int
) {
    Button(
        modifier = modifier,
        onClick = onClick,
        enabled = enabled,
        colors = ButtonDefaults.buttonColors(
            containerColor = Black,
            contentColor = Color.White,
            disabledContainerColor = Black.copy(alpha = 0.5f), // Теперь заблокированное состояние видно
            disabledContentColor = Color.White.copy(alpha = 0.5f)
        ),
        shape = RoundedCornerShape(8.dp) // Соответствует макету со скруглениями
    ) {
        Text(
            text = textInButton,
            color = Color.White,
            fontSize = textFontSize.sp,
            fontFamily = FontFamily(Font(R.font.nunito_sans)),
            letterSpacing = 0.sp
        )
    }
}

@Preview(showSystemUi = true)
@Composable
private fun MainButtonPrev() {
    MainButton(
        textInButton = "Get Started",
        textFontSize = 18

    )
}