package com.example.retrofit.ui.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.retrofit.ui.screen.CartScreen
import com.example.retrofit.ui.screen.EditCartScreen
import com.example.retrofit.ui.screen.LoginScreen
import com.example.retrofit.ui.screen.ProfileScreen
import com.example.retrofit.ui.theme.viewModel.AuthViewModel

@Composable
fun AppNavGraph() {
    val navController = rememberNavController()
    val authViewModel: AuthViewModel = viewModel()

    NavHost(
        navController = navController,
        startDestination = CartsRoute
    ) {
        composable<LoginRoute> {
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
                },
                onProfileClick = {
                    navController.navigate(ProfileRoute)
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

        composable<ProfileRoute> {
            ProfileScreen(
                user = authViewModel.currentUser,
                onHomeClick = {
                    navController.popBackStack()
                },
                onBackClick = {
                    navController.popBackStack()
                },
                onLogoutClick = {
                    authViewModel.logout {
                        navController.navigate(LoginRoute) {
                            popUpTo<CartsRoute> { inclusive = true }
                        }
                    }
                }
            )
        }
    }
}