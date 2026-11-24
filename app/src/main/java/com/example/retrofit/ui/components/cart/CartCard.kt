package com.example.retrofit.ui.components.cart

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.retrofit.data.model.Cart
import com.example.retrofit.ui.components.text.SecondaryText
import com.example.retrofit.ui.theme.RetrofitTheme
import java.util.Locale

@Composable
fun CartCard(
    cart: Cart,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
    showTotalValue: Boolean = true
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(Color.White, RoundedCornerShape(8.dp))
            .clickable { onClick() }
            .padding(horizontal = 20.dp, vertical = 15.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            SecondaryText(
                text = "Products:",
                fontSize = 18.sp,
                color = Color(0xFF808080)
            )
            SecondaryText(
                text = cart.totalProducts.toString(),
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF242424)
            )
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            SecondaryText(
                text = "Total:",
                fontSize = 18.sp,
                color = Color(0xFF808080)
            )
            if (showTotalValue) {
                SecondaryText(
                    text = String.format(Locale.US, "$ %.2f", cart.total),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF242424)
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
private fun CartCardPreview() {
    RetrofitTheme {
        Box(
            modifier = Modifier
                .background(Color(0xFFF5F5F5))
                .padding(20.dp)
        ) {
            CartCard(cart = Cart("1", 4, 100.0, "", ""))
        }
    }
}
