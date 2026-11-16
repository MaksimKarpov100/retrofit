package com.example.retrofit.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.retrofit.ui.components.cart.CartCard
import com.example.retrofit.ui.theme.viewModel.CartsViewModel

@Composable
fun CartScreen(cartsViewModel: CartsViewModel = viewModel()
) {
    LaunchedEffect(Unit) {
        cartsViewModel.loadCarts()
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxWidth(),
            contentPadding = PaddingValues(horizontal = 20.dp, vertical = 24.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        items(cartsViewModel.carts) {cart ->
            CartCard(cart = cart)
        }
    }


}
