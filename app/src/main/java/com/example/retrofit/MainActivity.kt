package com.example.retrofit

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.retrofit.data.model.LoginRequest
import com.example.retrofit.data.model.userRecord
import com.example.retrofit.ui.theme.RetrofitTheme
import com.example.retrofit.ui.theme.viewModel.AuthViewModel
import com.example.retrofit.ui.theme.viewModel.CartsViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RetrofitTheme {
//                val user = userRecord(
//                    "Maksim",
//                    "Karpov",
//                    "maksim@example.com",
//                    "maksim123",
//                    "maksim123"
//                )
                val login = LoginRequest(
                    "maksim@example.com",
                    "maksim123"
                )

//                val authViewModel: AuthViewModel = viewModel()
//                authViewModel.createNewUser(user)
                val getCartsViewModel: CartsViewModel = viewModel()

                LaunchedEffect(Unit) {
                    getCartsViewModel.getCart(login)

                }
            }
        }
    }
}