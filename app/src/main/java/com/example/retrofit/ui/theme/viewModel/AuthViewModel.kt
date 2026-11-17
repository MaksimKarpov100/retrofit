package com.example.retrofit.ui.theme.viewModel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.retrofit.data.RetrofitClient
import com.example.retrofit.data.TokenManager
import com.example.retrofit.data.model.LoginRequest
import kotlinx.coroutines.launch

class AuthViewModel : ViewModel() {

    var isLoading by mutableStateOf(false)
        private set
    var isSuccess by mutableStateOf(false)
        private set
    var errorMessage by mutableStateOf<String?>(null)
        private set

    fun login(email: String, password: String) {
        viewModelScope.launch {
            isLoading = true
            errorMessage = null
            isSuccess = false

            try {
                val response = RetrofitClient.authService.login(
                    LoginRequest(identity = email, password = password)
                )
                val token = response.token
                TokenManager.saveToken(token)
                Log.d("AUTH", "Успешный вход. Токен: $token")

                isSuccess = true

            } catch (e: Exception) {
                Log.e("AUTH", "Ошибка авторизации: ${e.message}")

                errorMessage = "Неверный логин или пароль"

            } finally {
                isLoading = false
            }
        }
    }
    fun clearError() {
        errorMessage = null
    }
}