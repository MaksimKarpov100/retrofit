package com.example.retrofit.ui.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.retrofit.ui.components.BottomBar
import com.example.retrofit.ui.screen.CartScreen
import com.example.retrofit.ui.screen.EditCartScreen
import com.example.retrofit.ui.screen.LoginScreen
import com.example.retrofit.ui.screen.ProfileScreen
import com.example.retrofit.ui.theme.viewModel.AuthViewModel

@Composable
fun AppNavGraph() {
    val navController = rememberNavController()
    val authViewModel: AuthViewModel = viewModel()

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    val selectedTab = when {
        currentDestination?.hasRoute<CartsRoute>() == true -> 0
        currentDestination?.hasRoute<ProfileRoute>() == true -> 3
        else -> -1
    }

    Scaffold(
        bottomBar = {
            if (selectedTab != -1) {
                BottomBar(
                    selectedTab = selectedTab,
                    onTabSelected = { index ->
                        when (index) {
                            0 -> navController.navigate(CartsRoute) {
                                popUpTo<CartsRoute> { saveState = true }
                                launchSingleTop = true
                                restoreState = true
                            }
                            3 -> navController.navigate(ProfileRoute) {
                                popUpTo<CartsRoute> { saveState = true }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    }
                )
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = LoginRoute,
            modifier = Modifier.padding(innerPadding)
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
}
