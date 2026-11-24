package com.example.retrofit.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.retrofit.R
import com.example.retrofit.data.model.Cart
import com.example.retrofit.ui.components.button.MainButton
import com.example.retrofit.ui.components.cart.CartCard
import com.example.retrofit.ui.components.text.SecondaryText
import com.example.retrofit.ui.theme.RetrofitTheme
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

    val cart = cartsViewModel?.editableCart ?: Cart("1", 0, 0.0, "", "")

    LaunchedEffect(cartsViewModel?.editableCart) {
        cartsViewModel?.editableCart?.let {
            inputTotal = it.total.toString()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5F5))
            .padding(20.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White, RoundedCornerShape(8.dp))
                .padding(bottom = 15.dp)
        ) {
            CartCard(
                cart = cart,
                showTotalValue = false
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .height(66.dp)
                    .border(1.dp, Color(0xFFDBDBDB), RoundedCornerShape(4.dp))
                    .background(Color.White, RoundedCornerShape(4.dp))
                    .padding(horizontal = 14.dp, vertical = 10.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                SecondaryText(
                    text = "Total",
                    fontSize = 12.sp,
                    color = Color(0xFF808080)
                )

                BasicTextField(
                    value = inputTotal,
                    onValueChange = { inputTotal = it },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(),
                    textStyle = TextStyle(
                        color = Color(0xFF242424),
                        fontSize = 16.sp,
                        fontFamily = FontFamily(Font(R.font.nunito_sans)),
                        fontWeight = FontWeight.SemiBold
                    ),
                    decorationBox = { innerTextField ->
                        Box {
                            if (inputTotal.isEmpty()) {
                                SecondaryText(
                                    text = "Type total price",
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.SemiBold,
                                    color = Color(0xFFB3B3B3)
                                )
                            }
                            innerTextField()
                        }
                    }
                )
            }
        }

        Spacer(modifier = Modifier.height(26.dp))

        MainButton(
            textInButton = "Save",
            onClick = {
                val newTotal = inputTotal.toDoubleOrNull()
                if (newTotal != null) {
                    cartsViewModel?.saveCart(cartId, newTotal) {
                        onBack()
                    }
                }
            },
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
private fun EditCartScreenPreview() {
    RetrofitTheme {
        EditCartScreen(cartId = "1", onBack = {}, cartsViewModel = null)
    }
}
