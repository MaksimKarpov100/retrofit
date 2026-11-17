package com.example.retrofit

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.retrofit.data.TokenManager
import com.example.retrofit.data.model.LoginRequest
import com.example.retrofit.ui.navigation.AppNavGraph
import com.example.retrofit.ui.theme.RetrofitTheme
import com.example.retrofit.ui.theme.viewModel.CartsViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        TokenManager.init(applicationContext)

        enableEdgeToEdge()
        setContent {
            RetrofitTheme {
                Surface(modifier = Modifier.fillMaxSize(), color = Color.White) {
                    AppNavGraph()
                }

//                CartScreen()
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