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
fun SecondaryText(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = Color(0xFF808080),
    fontSize: TextUnit = 14.sp,
    fontWeight: FontWeight = FontWeight.Normal
) {
    Text(
        text = text,
        modifier = modifier,
        color = color,
        fontSize = fontSize,
        fontFamily = FontFamily(Font(R.font.merriweather)),
        fontWeight = fontWeight
    )
}

@Preview
@Composable
private fun SecondaryTextPrev() {
    SecondaryText(text = "HELLO")

}