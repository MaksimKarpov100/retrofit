package com.example.retrofit.ui.navigation

import CartsRoute
import EditCartRoute
import LoginRoute
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.retrofit.ui.screen.CartScreen
import com.example.retrofit.ui.screen.EditCartScreen
import com.example.retrofit.ui.screen.LoginScreen
import com.example.retrofit.ui.theme.viewModel.AuthViewModel

@Composable
fun AppNavGraph() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = LoginRoute
    ) {
        composable<LoginRoute> {
            val authViewModel: AuthViewModel = viewModel()

            LoginScreen(
                viewModel = authViewModel,
                onLoginSuccess = {
                    navController.navigate(CartsRoute) {
                        popUpTo<LoginRoute> { inclusive = true }
                    }
                }
            )
        }

        composable<CartsRoute> {
            CartScreen(
                onItemClick = { id ->
                    navController.navigate(EditCartRoute(cartId = id))
                }
            )
        }

        composable<EditCartRoute> { backStackEntry ->
            val route: EditCartRoute = backStackEntry.toRoute()

            EditCartScreen(
                cartId = route.cartId,
                onBack = {
                    navController.popBackStack()
                }
            )
        }
    }
}
