package com.example.retrofit.ui.components.texts

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.retrofit.R
import com.example.retrofit.ui.theme.BlackFont

@Composable
fun MainText(
    modifier: Modifier = Modifier,
    text: String,
    fontSize: Int
) {
    Text(
        modifier = modifier,
        text = text,
        color = BlackFont,
        fontSize = fontSize.sp,
        letterSpacing = 0.sp,
        fontFamily = FontFamily(Font(R.font.merriweather)),
        fontWeight = FontWeight.Normal,
        lineHeight = 45.sp
    )
}

@Preview(showSystemUi = true)
@Composable
private fun MainTextPrev() {
    MainText(text = "Hello !", fontSize = 30)
}
