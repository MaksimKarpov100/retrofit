package com.example.retrofit.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
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
fun CartScreen(cartsViewModel: CartsViewModel = viewModel()) {

    LaunchedEffect(Unit) {
        cartsViewModel.loadCarts()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF7F7F7))
            .padding(top = 24.dp)
    ) {
        if (cartsViewModel.isLoading) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator(color = Color.Black)
            }
        } else {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(horizontal = 20.dp, vertical = 8.dp),
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                items(cartsViewModel.carts) { cart ->
                    CartCard(cart = cart)
                }
            }
        }
    }
}


@Preview(showBackground = true, backgroundColor = 0xFFF7F7F7)
@Composable
private fun CartScreenPreview() {
    RetrofitTheme {
        val fakeViewModel = CartsViewModel().apply {
            carts = listOf(
                Cart("1", 4, 100.0, "2026-11-10", "2026-11-10"),
                Cart("2", 2, 45.5, "2026-11-10", "2026-11-10")
            )
            isLoading = false
        }
        CartScreen(cartsViewModel = fakeViewModel)
    }
}