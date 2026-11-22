package com.example.retrofit.ui.components.cart

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.retrofit.data.model.Cart
import com.example.retrofit.ui.theme.CardBackground
import com.example.retrofit.ui.theme.RetrofitTheme
import com.example.retrofit.ui.theme.ShadowColor
import java.util.Locale

@Composable
fun CartCard(
    cart: Cart,
    onClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(12.dp, RoundedCornerShape(8.dp), ambientColor = ShadowColor, spotColor = ShadowColor)
            .background(CardBackground, RoundedCornerShape(8.dp))
            .clickable { onClick() }
            .padding(horizontal = 20.dp, vertical = 14.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text("Products:", fontSize = 16.sp, color = Color(0xFF808080))
            Text(
                text = cart.totalProducts.toString(),
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF242424)
            )
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text("Total:", fontSize = 16.sp, color = Color(0xFF808080))
            Text(
                text = String.format(Locale.US, "$ %.2f", cart.total),
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF242424)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun CartCardPreview() {
    RetrofitTheme {
        CartCard(cart = Cart("1", 4, 100.0, "", ""), onClick = {})
    }
}
