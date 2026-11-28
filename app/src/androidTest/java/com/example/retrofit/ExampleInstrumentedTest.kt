package com.example.retrofit

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.isDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.retrofit.data.TokenManager
import com.example.retrofit.ui.navigation.AppNavGraph
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class LoginScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Before
    fun setUp() {
        TokenManager.init(ApplicationProvider.getApplicationContext())
        composeTestRule.setContent {
            AppNavGraph()
        }
    }

    @Test
    fun checkFieldsAndButton() {
        composeTestRule.onNodeWithTag("FieldEmail").performTextInput("maksim@example.com")
        composeTestRule.onNodeWithTag("FieldPassword").performTextInput("maksim123")

        composeTestRule.onNodeWithTag("ButtonLogin").assertIsDisplayed()
        composeTestRule.onNodeWithTag("ButtonLogin").assertIsEnabled()
    }

    @Test
    fun checkInvalidLogin() {
        composeTestRule.onNodeWithTag("FieldEmail").performTextInput("wrong@example.com")
        composeTestRule.onNodeWithTag("FieldPassword").performTextInput("wrong123")
        composeTestRule.onNodeWithTag("ButtonLogin").performClick()

        composeTestRule.waitUntil(5000) {
            composeTestRule.onNodeWithText("Неверный логин или пароль").isDisplayed()
        }
        composeTestRule.onNodeWithText("WELCOME BACK").assertIsDisplayed()
    }

    @Test
    fun checkValidLogin() {
        composeTestRule.onNodeWithTag("FieldEmail").performTextInput("maksim@example.com")
        composeTestRule.onNodeWithTag("FieldPassword").performTextInput("maksim123")
        composeTestRule.onNodeWithTag("ButtonLogin").performClick()

        composeTestRule.waitUntil(5000) {
            composeTestRule.onNodeWithText("Make home\nBEAUTIFUL").isDisplayed()
        }
        composeTestRule.onNodeWithText("Make home\nBEAUTIFUL").assertIsDisplayed()
    }
}
