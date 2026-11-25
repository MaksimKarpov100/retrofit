package com.example.retrofit.ui.components.text

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import com.example.retrofit.R

@Composable
fun PrimaryText(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = Color(0xFF303030),
    fontSize: TextUnit = 24.sp,
    fontWeight: FontWeight = FontWeight.Bold,
    letterSpacing: TextUnit = 1.2.sp,
    fontFamily: FontFamily = FontFamily(
        Font(R.font.merriweather, FontWeight.Normal),
        Font(R.font.merriweather_bold, FontWeight.Bold)
    )
) {
    Text(
        text = text,
        modifier = modifier,
        color = color,
        fontSize = fontSize,
        fontFamily = fontFamily,
        fontWeight = fontWeight,
        letterSpacing = letterSpacing,
        lineHeight = 45.sp
    )
}

@Preview(showBackground = true)
@Composable
private fun PrimaryTextPrev() {
    PrimaryText(text = "WELCOME BACK")
}
