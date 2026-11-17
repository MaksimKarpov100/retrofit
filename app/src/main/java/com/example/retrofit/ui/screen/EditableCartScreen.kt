package com.example.retrofit.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.retrofit.R
import com.example.retrofit.data.model.Cart
import com.example.retrofit.ui.components.cart.EditableCartCard
import com.example.retrofit.ui.theme.RetrofitTheme
import com.example.retrofit.ui.theme.White

@Composable
fun EditableCartScreen() {
    val fakeCart = Cart(
        "1",
        4,
        100.0,
        "2026-11-10",
        "2026-11-10"
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF7F7F7))
            .padding(20.dp)
    ) {
        EditableCartCard(cart = fakeCart)

        Spacer(modifier = Modifier.height(26.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .shadow(
                    elevation = 10.dp,
                    shape = RoundedCornerShape(8.dp),
                    ambientColor = Color(0xFF303030).copy(alpha = 0.3f),
                    spotColor = Color(0xFF303030).copy(alpha = 0.3f)
                )
                .background(Color(0xFF242424), RoundedCornerShape(8.dp))
                .clickable {},
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Save",
                color = White,
                fontSize = 18.sp,
                fontFamily = FontFamily(Font(R.font.merriweather_bold))
            )
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFF7F7F7)
@Composable
private fun EditableCartScreenPreview() {
    RetrofitTheme {
        EditableCartScreen()
    }
}