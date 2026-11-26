package com.example.retrofit.ui.theme.viewModel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.retrofit.data.RetrofitClient
import com.example.retrofit.data.TokenManager
import com.example.retrofit.data.model.Cart
import kotlinx.coroutines.launch

class CartsViewModel : ViewModel() {
    var carts by mutableStateOf<List<Cart>>(emptyList())
    var editableCart by mutableStateOf<Cart?>(null)

    fun loadCarts() {
        viewModelScope.launch {
            try {
                val token = TokenManager.getToken()
                val response = RetrofitClient.cartService.getCarts(token)
                if (token != null) {
                    carts = RetrofitClient.cartService.getCarts(token).items
                    for (cart in response.items) {
                        Log.d("CARTS", "id: ${cart.id}")
                        Log.d("CARTS", "totalProducts: ${cart.totalProducts}")
                        Log.d("CARTS", "total: ${cart.total}")
                        Log.d("CARTS", "created: ${cart.created}")
                        Log.d("CARTS", "updated: ${cart.updated}")
                    }
                } else {
                    Log.d("", "Ошибка! Токен пуст")
                }
            } catch (e: Exception) {
                Log.e("CartsVM", "Ошибка загрузки списка: ${e.message}")
            }
        }
    }

    fun loadCartById(id: String) {
        viewModelScope.launch {
            try {
                val token = TokenManager.getToken()
                if (token != null) {
                    editableCart = RetrofitClient.cartService.getCartById(token, id)
                }
            } catch (e: Exception) {
                Log.e("CartsVM", "Ошибка загрузки объекта: ${e.message}")
            }
        }
    }

    fun saveCart(id: String, newTotal: Double, onSuccess: () -> Unit) {
        viewModelScope.launch {
            try {
                val token = TokenManager.getToken()
                val current = editableCart
                if (token != null && current != null) {
                    val updatedCart = current.copy(total = newTotal)
                    RetrofitClient.cartService.updateCart(token, id, updatedCart)
                    onSuccess()
                }
            } catch (e: Exception) {
                Log.e("CartsVM", "Ошибка сохранения: ${e.message}")
            }
        }
    }
}
