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
import com.example.retrofit.data.model.UserRecord
import kotlinx.coroutines.launch

class AuthViewModel : ViewModel() {

    var isSuccess by mutableStateOf(false)
    var errorMessage by mutableStateOf<String?>(null)
    var currentUser by mutableStateOf<UserRecord?>(null)

    fun login(email: String, password: String) {
        viewModelScope.launch {
            errorMessage = null
            isSuccess = false

            try {
                val response = RetrofitClient.authService.login(
                    LoginRequest(identity = email, password = password)
                )
                TokenManager.saveToken(response.token)
                currentUser = response.record
                isSuccess = true
                Log.d("AUTH","Токен сохранен")
            } catch (e: Exception) {
                Log.e("AUTH", "Ошибка авторизации: ${e.message}")
                errorMessage = "Неверный логин или пароль"
            }
        }
    }

    fun logout(onSuccess: () -> Unit) {
        viewModelScope.launch {
            TokenManager.saveToken("")
            currentUser = null
            isSuccess = false
            onSuccess()
        }
    }

    fun clearError() {
        errorMessage = null
    }
}
