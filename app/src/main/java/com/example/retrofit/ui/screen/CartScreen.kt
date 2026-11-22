package com.example.retrofit.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.retrofit.data.model.Cart
import com.example.retrofit.ui.components.cart.CartCard
import com.example.retrofit.ui.theme.RetrofitTheme
import com.example.retrofit.ui.theme.viewModel.CartsViewModel

@Composable
fun CartScreen(
    cartsViewModel: CartsViewModel? = viewModel(),
    onItemClick: (String) -> Unit = {}
) {
    LaunchedEffect(Unit) {
        cartsViewModel?.loadCarts()
    }

    val carts = cartsViewModel?.carts ?: listOf(
        Cart("1", 4, 100.0, "", ""),
        Cart("2", 2, 45.5, "", "")
    )

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF7F7F7))
            .padding(top = 24.dp),
        contentPadding = PaddingValues(horizontal = 20.dp, vertical = 8.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        items(carts) { cart ->
            CartCard(
                cart = cart,
                onClick = { onItemClick(cart.id) }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun CartScreenPreview() {
    RetrofitTheme {
        CartScreen(cartsViewModel = null)
    }
}
