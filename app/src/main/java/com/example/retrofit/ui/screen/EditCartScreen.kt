package com.example.retrofit.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.retrofit.R
import com.example.retrofit.data.model.Cart
import com.example.retrofit.ui.theme.CardBackground
import com.example.retrofit.ui.theme.RetrofitTheme
import com.example.retrofit.ui.theme.ShadowColor
import com.example.retrofit.ui.theme.White
import com.example.retrofit.ui.theme.viewModel.CartsViewModel

@Composable
fun EditCartScreen(
    cartId: String,
    onBack: () -> Unit,
    cartsViewModel: CartsViewModel? = viewModel()
) {
    var inputTotal by remember { mutableStateOf("") }

    LaunchedEffect(cartId) {
        cartsViewModel?.loadCartById(cartId)
    }

    val cart = cartsViewModel?.editableCart ?: Cart("1", 4, 100.0, "", "")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF7F7F7))
            .padding(20.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .shadow(12.dp, RoundedCornerShape(8.dp), ambientColor = ShadowColor, spotColor = ShadowColor)
                .background(CardBackground, RoundedCornerShape(8.dp))
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

            Text("Total:", fontSize = 16.sp, color = Color(0xFF808080))

            OutlinedTextField(
                value = inputTotal,
                onValueChange = { inputTotal = it },
                label = { Text("Total") },
                placeholder = { Text("Type total price", color = Color(0xFFB3B3B3)) },
                shape = RoundedCornerShape(4.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color(0xFFDBDBDB),
                    unfocusedBorderColor = Color(0xFFDBDBDB),
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(66.dp)
            )
        }

        Spacer(modifier = Modifier.height(26.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .shadow(10.dp, RoundedCornerShape(8.dp))
                .background(Color(0xFF242424), RoundedCornerShape(8.dp))
                .clickable {
                    val newTotal = inputTotal.toDoubleOrNull()
                    if (newTotal != null) {
                        cartsViewModel?.saveCart(cartId, newTotal) { onBack() }
                    }
                },
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

@Preview(showBackground = true)
@Composable
private fun EditCartScreenPreview() {
    RetrofitTheme {
        EditCartScreen(cartId = "1", onBack = {}, cartsViewModel = null)
    }
}
