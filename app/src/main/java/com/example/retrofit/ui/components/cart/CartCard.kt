package com.example.retrofit.ui.components.cart

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.retrofit.R
import com.example.retrofit.data.model.Cart
import com.example.retrofit.ui.theme.CardBackground
import com.example.retrofit.ui.theme.NunitoSansFontFamily
import com.example.retrofit.ui.theme.ShadowColor
import com.example.retrofit.ui.theme.TextPrimary

@Composable
fun CartCard(cart: Cart ) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height((80.dp))
            .shadow(
                elevation = 8.dp,
                shape = RoundedCornerShape((8.dp)),
                ambientColor = ShadowColor,
                spotColor = ShadowColor
            )
            .background(
                color = CardBackground,
                shape = RoundedCornerShape(8.dp)
            )
            .padding(horizontal = 20.dp, vertical = 16.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.Center
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Products",
                    color = TextPrimary,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Normal,
                    lineHeight = 18.sp,
                    fontFamily = FontFamily(Font(R.font.nunito_sans)
                    )
                )
                Text(
                    text = "${cart.totalProducts}",
                    color = TextPrimary,
                    fontSize = 18.sp,
                    lineHeight = 18.sp,
                    fontWeight = FontWeight.SemiBold,
                    textAlign = TextAlign.Right,
                    fontFamily = FontFamily(Font(R.font.nunito_sans)
                    )
                )

            }
            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Total",
                    color = TextPrimary,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Normal,
                    lineHeight = 18.sp,
                    fontFamily = FontFamily(Font(R.font.nunito_sans)
                    )
                )
                Text(
                    text = "$${cart.total}",
                    color = TextPrimary,
                    fontSize = 18.sp,
                    lineHeight = 18.sp,
                    fontWeight = FontWeight.SemiBold,
                    textAlign = TextAlign.Right,
                    fontFamily = FontFamily(Font(R.font.nunito_sans)
                    )
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun CartCardPrev() {
    val fakeCart = Cart(
        id="1",
        totalProducts = 4,
        total = 100.0,
        created = "2026-11-10",
        updated = "2026-11-10"
    )
    Box(modifier = Modifier.padding(24.dp)){
        CartCard(cart = fakeCart)
    }

}