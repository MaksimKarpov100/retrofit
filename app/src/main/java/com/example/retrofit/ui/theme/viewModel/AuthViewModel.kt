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

    var isLoading by mutableStateOf(false)
    var errorMesage by mutableStateOf<String?>(null)
    var isSuccess by mutableStateOf(false)

    fun createNewUser(userRecord: UserRecord){
        viewModelScope.launch{
            try {
                val newUser = RetrofitClient.authService.createNewUser(userRecord)
                Log.d("AuthViewModel", "${newUser.email}, ${newUser.password}")

            } catch (ex: Exception) {
                Log.e("AuthViewModel", "${ex.message}")
            }
        }

    }

    fun login(email: String, password: String){
        viewModelScope.launch {
            isLoading = true
            errorMesage = null
            isSuccess = false

            try{
                val response = RetrofitClient.authService.login(
                    LoginRequest(identity = email, password = password)
                )

                val token = response.token
                Log.d("AUTH", "Токен получен: $token")

                TokenManager.saveToken((token))
                isSuccess = true

            } catch (ex: Exception) {
                Log.e("AUTH", "Ошибка авторизации${ex.message}")
                errorMesage = ex.message ?: "Ошибка подключения к серверу"
            } finally {
                isLoading = false
            }
        }
    }

    fun clearError(){
        errorMesage = null
    }


}