package com.example.retrofit.ui.theme.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.retrofit.data.RetrofitClient
import com.example.retrofit.data.model.User
import kotlinx.coroutines.launch

class UsersViewModel : ViewModel() {

    fun addUser(newUser: User) {
        viewModelScope.launch {
            try {
                val user = RetrofitClient.userService.addUser(newUser)

                Log.d("UserResponse", "id: ${user.id}")
                Log.d("UserResponse", "firstName: ${user.firstName}")
                Log.d("UserResponse", "lastName: ${user.lastName}")
                Log.d("UserResponse", "company: ${user.company.name}")
                Log.d("UserResponse", "title: ${user.company.title}")

            } catch (e: Exception) {
                Log.e("UserResponse", "error: ${e.message}")
            }
        }
    }
}