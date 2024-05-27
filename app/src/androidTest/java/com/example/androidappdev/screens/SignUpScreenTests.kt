package com.example.androidappdev.screens

import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import com.example.androidappdev.R
import org.junit.Before
import org.junit.FixMethodOrder
import org.junit.Test
import org.junit.runners.MethodSorters

@FixMethodOrder(MethodSorters.DEFAULT)
class SignUpScreenTests : ScreenTests() {

    @Before
    override fun setUp() {
        super.setUp()
    }

    @Test
    fun `check default state of the sign up screen`() {
        rule.onNode(signUpButton).performClick()
        //on sign up page
        val pageTitle =
            hasText(rule.activity.getString(R.string.sign_up_screen_title))
        rule.onNode(pageTitle).assertExists()
        rule.onNode(bottomNavBar).assertDoesNotExist()
        rule.onNode(emailAddressTextField).assertExists()
        rule.onNode(passwordTextField).assertExists()
        rule.onNode(signUpButton).assertExists()
    }

    @Test
    fun `enter valid sign up details`() {
        rule.onNode(signUpButton).performClick()

        rule.onNode(firstNameTextField).performTextInput("firstName")
        rule.onNode(surnameTextField).performTextInput("surname")
        rule.onNode(emailAddressTextField)
            .performTextInput("newuser@email.com") //must be a valid email or firebase will put up an error via toast
        rule.onNode(passwordTextField).performTextInput("password")
        rule.onNode(signUpButton).performClick()
    }

    @Test
    fun `When invalid sign up details are provided the user is not able to sign up`() {
        rule.onNode(signUpButton).performClick()

        rule.onNode(firstNameTextField).performTextInput("")
        rule.onNode(surnameTextField).performTextInput("")
        rule.onNode(emailAddressTextField).performTextInput("")
        rule.onNode(passwordTextField).performTextInput("")
        rule.onNode(signUpButton).performClick()

        //check that the user is still on the sign up page
        val pageTitle =
            hasText(rule.activity.getString(R.string.sign_up_screen_title))
        rule.onNode(pageTitle).assertExists()
    }

    @Test
    fun `When already a user is clicked the user is taken back to the sign in page`() {
        rule.onNode(signUpButton).performClick()
        rule.onNode(alreadyAUserButton).performClick()

        //on sign in page
        rule.onNode(bottomNavBar).assertDoesNotExist()
        rule.onNode(emailAddressTextField).assertExists()
        rule.onNode(passwordTextField).assertExists()
        rule.onNode(signInButton).assertExists()
    }
}