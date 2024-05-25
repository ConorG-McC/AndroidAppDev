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

    //@Test
    fun `enter invalid sign up details`() {
        //tba
    }
}