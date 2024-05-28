package com.example.androidappdev.screens

import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.androidappdev.R
import org.junit.Before
import org.junit.FixMethodOrder
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.MethodSorters

@FixMethodOrder(MethodSorters.DEFAULT)
@RunWith(AndroidJUnit4::class)
open class LoginScreenTests : ScreenTests() {

    @Before
    override fun setUp() {
        super.setUp()
    }

    @Test
    fun `check state of the login in page`() {
        rule.onNode(emailAddressTextField).assertExists()
        rule.onNode(passwordTextField).assertExists()

        rule.onNode(signInButton).assertExists()
        rule.onNode(forgotPasswordButton).assertExists()
        rule.onNode(signUpButton).assertExists()

    }

    @Test
    fun `attempt sign in with invalid details`() {
        `sign in`(email = "invalid email", password = "invalid password")
        `check state of the login in page`()
    }

    @Test
    fun `move to the sign up page`() {
        rule.onNode(signUpButton).performClick()
        //on sign up page
        val pageTitle =
            hasText(rule.activity.getString(R.string.sign_up_screen_title))
        rule.onNode(pageTitle).assertExists()
    }

    @Test
    fun `check if user can sign in and proceed to the home page`() {
        `sign in`(email = "newuser@email.com", password = "password")
        rule.onNode(bottomNavBar).assertExists()
    }


    @Test
    fun `valid request for forgot password with email`() {
        rule.onNode(emailAddressTextField).performTextInput("newuser@email.com")
        rule.onNode(forgotPasswordButton).performClick()
        rule.onNode(emailAddressTextField).assertExists()

    }

    @Test
    fun `Error message is shown when invalid request for forgot password is attempted`() {
        var ERROR_MESSAGE_TEXT = "Please insert a valid email."
        rule.onNode(emailAddressTextField).performTextInput("")
        rule.onNode(forgotPasswordButton).performClick()
        rule.onNodeWithText(ERROR_MESSAGE_TEXT).assertExists()
    }
}
//https://developer.android.com/training/testing/espresso/idling-resource
