package com.example.retrofit.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.retrofit.R
import com.example.retrofit.ui.components.HeaderBar
import com.example.retrofit.ui.components.cart.CartCard
import com.example.retrofit.ui.theme.RetrofitTheme
import com.example.retrofit.ui.theme.viewModel.CartsViewModel

@Composable
fun CartScreen(
    onItemClick: (String) -> Unit,
    cartsViewModel: CartsViewModel? = viewModel()
) {
    LaunchedEffect(Unit) {
        cartsViewModel?.loadCarts()
    }

    val carts = cartsViewModel?.carts ?: emptyList()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5F5))
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .statusBarsPadding()
                .padding(vertical = 4.dp),
            contentAlignment = Alignment.Center
        ) {
            HeaderBar(
                title = "",
                leftIconRes = R.drawable.search_icon,
                rightIconRes = R.drawable.order
            )

            Text(
                text = buildAnnotatedString {
                    withStyle(
                        SpanStyle(
                            color = Color(0xFF909090),
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Normal
                        )
                    ) {
                        append("Make home\n")
                    }
                    withStyle(
                        SpanStyle(
                            color = Color(0xFF242424),
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold
                        )
                    ) {
                        append("BEAUTIFUL")
                    }
                },
                textAlign = TextAlign.Center,
                lineHeight = 25.sp,
                fontFamily = FontFamily(Font(R.font.gelasio))
            )
        }

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(20.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(carts) { cart ->
                CartCard(
                    cart = cart,
                    onClick = { onItemClick(cart.id) }
                )
            }
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
private fun CartScreenPreview() {
    RetrofitTheme {
        CartScreen(
            onItemClick = {},
            cartsViewModel = null
        )
    }
}
