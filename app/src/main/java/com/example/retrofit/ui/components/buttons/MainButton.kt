package com.example.retrofit.ui.components.buttons

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.retrofit.R

@Composable
fun MainButton(
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    onClick: () -> Unit = {},
    textInButton: String,
    textFontSize: Int = 18
) {
    Button(
        onClick = onClick,
        enabled = enabled,
        modifier = modifier
            .width(285.dp)
            .height(50.dp)
            .shadow(
                elevation = 20.dp,
                shape = RoundedCornerShape(8.dp),
                clip = false,
                ambientColor = Color(0x40303030),
                spotColor = Color(0x40303030)
            ),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFF242424),
            contentColor = Color.White,
            disabledContainerColor = Color(0xFF242424).copy(alpha = 0.5f),
            disabledContentColor = Color.White.copy(alpha = 0.5f)
        ),
        shape = RoundedCornerShape(8.dp)
    ) {
        Text(
            text = textInButton,
            style = TextStyle(
                fontFamily = FontFamily(Font(R.font.nunito_sans, FontWeight.SemiBold)),
                fontWeight = FontWeight.W600,
                fontSize = textFontSize.sp,
                lineHeight = textFontSize.sp,
                letterSpacing = 0.sp,
                textAlign = TextAlign.Center
            )
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
