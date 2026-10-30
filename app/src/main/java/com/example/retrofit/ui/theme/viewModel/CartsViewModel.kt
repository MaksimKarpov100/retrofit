package com.example.retrofit.ui.theme.viewModel

import android.media.session.MediaSession
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.retrofit.data.RetrofitClient
import com.example.retrofit.data.TokenManager
import com.example.retrofit.data.model.LoginRequest
import kotlinx.coroutines.launch

class CartsViewModel : ViewModel() {
    fun getCart(loginRequest: LoginRequest) {
        viewModelScope.launch {
            try {
                val auth = RetrofitClient.authService.login(loginRequest)
                val token = auth.token
                TokenManager.saveToken(token)
                val saveToken = TokenManager.getToken()
                if (saveToken != null) {
                    val carts = RetrofitClient.cartService.getCart(saveToken)
                    for (cart in carts.items) {
                        Log.d(
                            "CartsViewModel", "id: ${cart.id}\n" +
                                    " totalProducts: ${cart.totalProducts}\n" +
                                    " total: ${cart.total}\n" +
                                    " created: ${cart.created}\n" +
                                    " updated: ${cart.updated}\n" +
                                    " page: ${carts.page}\n" +
                                    " perPage: ${carts.perPage}\n" +
                                    " totalItems: ${carts.totalItems}\n" +
                                    " totalPages: ${carts.totalPages}"
                        )
                    }
                } else {
                    Log.d("AUTH", "Ошибка авторизации")
                }
            } catch (ex: Exception) {
                Log.d("CartsViewModel", "${ex.message}")
            }
        }
    }
}