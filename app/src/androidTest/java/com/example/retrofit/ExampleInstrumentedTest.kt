package com.example.retrofit

import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.testing.TestNavHostController
import androidx.test.ext.junit.runners.AndroidJUnit4

import com.example.retrofit.ui.screen.LoginScreen
import com.example.retrofit.ui.theme.RetrofitTheme
import com.example.retrofit.ui.theme.viewModel.AuthViewModel

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */


@RunWith(AndroidJUnit4::class)
class LoginScreenTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    private val viewModel = AuthViewModel()
    private var navController: TestNavHostController? = null

    private companion object {
        const val ROUTE_LOGIN = "login"
        const val ROUTE_HOME = "home"
    }

    @Before
    fun setupAppNavHost() {
        composeTestRule.setContent {
            val controller = TestNavHostController(LocalContext.current).apply {
                navigatorProvider.addNavigator(ComposeNavigator())
            }
            navController = controller

            NavHost(
                navController = controller,
                startDestination = ROUTE_LOGIN
            ) {
                composable(route = ROUTE_LOGIN) {
                    LoginScreen(
                        viewModel = viewModel,
                        onLoginSuccess = {
                            controller.navigate(ROUTE_HOME)
                        }
                    )
                }
                composable(route = ROUTE_HOME) {
                }
            }
        }
    }

    @Test
    fun loginScreen_loginButtonIsEnabled() {
        composeTestRule.onNodeWithTag("FieldEmail")
            .performTextInput("user@example.com")
        composeTestRule.onNodeWithTag("FieldPassword")
            .performTextInput("password123")

        composeTestRule.onNodeWithTag("ButtonLogin")
            .assertIsDisplayed()
            .assertIsEnabled()
    }

    @Test
    fun loginScreen_invalidCredentials_staysOnLoginRoute() {
        composeTestRule.onNodeWithTag("FieldEmail")
            .performTextInput("user@example.com")
        composeTestRule.onNodeWithTag("FieldPassword")
            .performTextInput("password123")
        composeTestRule.onNodeWithTag("ButtonLogin")
            .performClick()

        composeTestRule.waitUntil(timeoutMillis = 5_000) {
            viewModel.errorMessage != null
        }

        val currentRoute = navController?.currentBackStackEntry?.destination?.route
        assertEquals(ROUTE_LOGIN, currentRoute)
    }

    @Test
    fun loginScreen_validCredentials_navigatesToHomeRoute() {
        composeTestRule.onNodeWithTag("FieldEmail")
            .performTextInput("maksim@example.com")
        composeTestRule.onNodeWithTag("FieldPassword")
            .performTextInput("maksim123")
        composeTestRule.onNodeWithTag("ButtonLogin")
            .performClick()

        composeTestRule.waitUntil(timeoutMillis = 5_000) {
            navController?.currentBackStackEntry?.destination?.route == ROUTE_HOME
        }

        val currentRoute = navController?.currentBackStackEntry?.destination?.route
        assertEquals(ROUTE_HOME, currentRoute)
    }
}