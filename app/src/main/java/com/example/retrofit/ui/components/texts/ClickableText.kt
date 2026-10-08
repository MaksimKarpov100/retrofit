package com.example.retrofit.ui.components.texts

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.retrofit.R
import com.example.retrofit.ui.theme.BlackFont
import com.example.retrofit.ui.theme.White

@Composable
fun ClickableText(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit,
    fontSize: Int
) {
    Text(
        text = text,
        modifier = modifier.clickable(onClick = onClick).background(color = White),
        color = BlackFont,
        fontSize = fontSize.sp,
        textAlign = TextAlign.Center,
        letterSpacing = 0.sp,
        fontFamily = FontFamily(Font(R.font.nunito_sans))
    )
}

@Preview(showSystemUi = true)
@Composable
private fun ClickableTextPrev() {
    ClickableText(
        onClick = {},
        text = "adada",
        fontSize = 18
    )
}
