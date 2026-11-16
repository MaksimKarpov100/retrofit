package com.example.retrofit.ui.theme.viewModel

import android.media.session.MediaSession
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
    fun getCart(loginRequest: LoginRequest) {
        viewModelScope.launch {
            try {
                val auth = RetrofitClient.authService.login(loginRequest)
                val token = auth.token
                TokenManager.saveToken(token)
                Log.d("CartsViewModel", "Токен сохранен")
                val savedToken = TokenManager.getToken()
                if (savedToken != null) {
                    val carts = RetrofitClient.cartService.getCart(savedToken)
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
    fun loadCarts(){
        viewModelScope.launch {
            try{
                val token = TokenManager.getToken()
                if (token != null){
                    val response = RetrofitClient.cartService.getCart(token)
                    carts = response.items
                }
            } catch (e: Exception){
                Log.e("Carts", "Ошибка ${e.message}")
            }
        }
    }
}