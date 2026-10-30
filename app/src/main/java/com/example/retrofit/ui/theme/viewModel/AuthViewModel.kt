package com.example.retrofit.ui.theme.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.retrofit.data.RetrofitClient
import com.example.retrofit.data.model.userRecord
import kotlinx.coroutines.launch

class AuthViewModel : ViewModel() {

    fun createNewUser(userRecord: userRecord){
        viewModelScope.launch{
            try {
                val newUser = RetrofitClient.authService.createNewUser(userRecord)
                Log.d("AuthViewModel", "${newUser.email}, ${newUser.password}")

            } catch (ex: Exception) {
                Log.e("AuthViewModel", "${ex.message}")
            }
        }

    }
}