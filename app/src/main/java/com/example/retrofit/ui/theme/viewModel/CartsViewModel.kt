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
import com.example.retrofit.data.model.LoginRequest
import kotlinx.coroutines.launch

class CartsViewModel : ViewModel() {

    var carts by mutableStateOf<List<Cart>>(emptyList())
    var isLoading by mutableStateOf(false)
    var errorMessage by mutableStateOf<String?>(null)

    fun getCart(loginRequest: LoginRequest) {
        viewModelScope.launch {
            isLoading = true
            try {
                val auth = RetrofitClient.authService.login(loginRequest)
                val token = auth.token
                TokenManager.saveToken(token)
                Log.d("CartsViewModel", "Токен сохранен")
                val savedToken = TokenManager.getToken()
                if (savedToken != null) {
                    val response = RetrofitClient.cartService.getCart(savedToken)
                    carts = response.items
                    for (cart in response.items) {
                        Log.d("CartsViewModel", "id: ${cart.id}, total: ${cart.total}")
                    }
                }
            } catch (ex: Exception) {
                Log.d("CartsViewModel", "Ошибка: ${ex.message}")
                errorMessage = ex.message
            } finally {
                isLoading = false
            }
        }
    }

    fun loadCarts() {
        viewModelScope.launch {
            isLoading = true
            try {
                val token = TokenManager.getToken()
                if (token != null) {
                    val response = RetrofitClient.cartService.getCart(token)
                    carts = response.items
                }
            } catch (e: Exception) {
                Log.e("Carts", "Ошибка ${e.message}")
                errorMessage = e.message
            } finally {
                isLoading = false
            }
        }
    }
}