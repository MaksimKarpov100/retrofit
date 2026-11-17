package com.example.retrofit.ui.components.cart

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.retrofit.R
import com.example.retrofit.data.model.Cart
import com.example.retrofit.ui.theme.CardBackground
import com.example.retrofit.ui.theme.RetrofitTheme
import com.example.retrofit.ui.theme.ShadowColor
import com.example.retrofit.ui.theme.TextPrimary
import java.util.Locale

@Composable
fun CartCard(cart: Cart) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 80.dp)
            .shadow(
                elevation = 12.dp,
                shape = RoundedCornerShape(8.dp),
                ambientColor = ShadowColor,
                spotColor = ShadowColor
            )
            .background(
                color = CardBackground,
                shape = RoundedCornerShape(8.dp)
            )
            .padding(horizontal = 20.dp, vertical = 12.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.Center
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Products:",
                    color = TextPrimary,
                    fontSize = 18.sp,
                    fontFamily = FontFamily(Font(R.font.nunito_sans))
                )
                Text(
                    text = "${cart.totalProducts}",
                    color = TextPrimary,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold,
                    fontFamily = FontFamily(Font(R.font.nunito_sans))
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Total:",
                    color = TextPrimary,
                    fontSize = 18.sp,
                    fontFamily = FontFamily(Font(R.font.nunito_sans))
                )
                Text(
                    text = "$ ${String.format(Locale.US, "%.2f", cart.total)}",
                    color = TextPrimary,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold,
                    fontFamily = FontFamily(Font(R.font.nunito_sans))
                )
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFF7F7F7)
@Composable
private fun CartCardPreview() {
    RetrofitTheme {
        Box(modifier = Modifier.padding(20.dp)) {
            CartCard(
                cart = Cart(
                    id = "1",
                    totalProducts = 4,
                    total = 100.0,
                    created = "2026-11-10",
                    updated = "2026-11-10"
                )
            )
        }
    }
}