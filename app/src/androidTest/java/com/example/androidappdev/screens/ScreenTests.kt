package com.example.androidappdev.screens

import androidx.compose.ui.test.SemanticsMatcher
import androidx.compose.ui.test.hasAnySibling
import androidx.compose.ui.test.hasContentDescription
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import com.example.androidappdev.R
import com.example.androidappdev.core.MainActivity
import com.example.androidappdev.presentation.navigation.NavScreen
import org.junit.Before
import org.junit.Rule

open abstract class ScreenTests {
    @get:Rule
    var rule = createAndroidComposeRule<MainActivity>()

    //Nav bar items
    val bottomNavBar = hasContentDescription("bottom_nav")
    val exitNavBarItem = hasText(NavScreen.Exit.route)

    val homeNavBarItem =
        hasText(NavScreen.Home.route) and hasAnySibling(exitNavBarItem)
    val employeeNavBarItem =
        hasText(NavScreen.Employees.route) and hasAnySibling(exitNavBarItem)
    val tasksNavBarItem =
        hasText(NavScreen.Tasks.route) and hasAnySibling(exitNavBarItem)

    //For login + signup screen
    lateinit var emailAddressTextField: SemanticsMatcher
    lateinit var passwordTextField: SemanticsMatcher
    lateinit var signInButton: SemanticsMatcher
    lateinit var forgotPasswordButton: SemanticsMatcher
    lateinit var signUpButton: SemanticsMatcher
    lateinit var firstNameTextField: SemanticsMatcher
    lateinit var surnameTextField: SemanticsMatcher
    lateinit var alreadyAUserButton: SemanticsMatcher

    //Data for add screen
//    val FIRST_NAME1 = "first1"
//    val SURNAME1 = "surname1"
//    val TELNO1 = "telNo1"
//


//    lateinit var telephoneNumberTextField: SemanticsMatcher
//    lateinit var addScreenText: SemanticsMatcher
//    lateinit var addButton: SemanticsMatcher

    //For home screen
//    val listItem = hasText("$FIRST_NAME1 $SURNAME1 $TELNO1")
    lateinit var homeScreenText: SemanticsMatcher

//    lateinit var deleteButton: SemanticsMatcher


//
//    //For edit screen
//    lateinit var editScreenText: SemanticsMatcher

    @Before
    open fun setUp() {
        val BUTTON_POSTFIX = " button"

        //Login + Sign up screen
        emailAddressTextField =
            hasContentDescription(rule.activity.getString(R.string.email))
        passwordTextField =
            hasContentDescription(rule.activity.getString(R.string.password))
        signInButton =
            hasContentDescription(rule.activity.getString(R.string.sign_in_button) + BUTTON_POSTFIX)
        forgotPasswordButton =
            hasContentDescription(rule.activity.getString(R.string.forgot_password) + BUTTON_POSTFIX)
        signUpButton =
            hasContentDescription(rule.activity.getString(R.string.sign_up_button) + BUTTON_POSTFIX)
        firstNameTextField =
            hasContentDescription(rule.activity.getString(R.string.first_name_hint))
        surnameTextField =
            hasContentDescription(rule.activity.getString(R.string.surname_hint))
        alreadyAUserButton =
            hasContentDescription(rule.activity.getString(R.string.already_a_user) + BUTTON_POSTFIX)

        homeScreenText = hasText(rule.activity.getString(R.string.home_button))


//        deleteButton =
//            hasContentDescription(rule.activity.getString(R.string.delete) + BUTTON_POSTFIX)
//        addButton =
//            hasContentDescription(rule.activity.getString(R.string.add) + " button")


//        firstNameTextField =
//            hasContentDescription(rule.activity.getString(R.string.first_name_hint))
//        surnameTextField =
//            hasContentDescription(rule.activity.getString(R.string.surname_hint))
//
//        addScreenText =
//            hasText(rule.activity.getString(R.string.add)) and hasNoClickAction()
//        editScreenText = hasText(rule.activity.getString(R.string.edit))
    }

    //Use for valid and invalid sign ins - use default values for generic log in
    fun `sign in`(email: String, password: String
    ) {
        //rule.onNode(emailAddressTextField).printToLog("UI_TEST");
        rule.onNode(emailAddressTextField).performTextInput(email)
        rule.onNode(passwordTextField).performTextInput(password)
        rule.onNode(signInButton).performClick()

        Thread.sleep(1000)//pause or the following will fail - recommendation is an idle call back (not demonstrated here)
    }

    //Used by add screen + home screen creating a user before editing
//    fun `enter_a_valid_user`() {
//        rule.onNode(firstNameTextField).performTextInput(FIRST_NAME1)
//        rule.onNode(surnameTextField).performTextInput(SURNAME1)
//
//        rule.onNode(addButton).performClick()
//    }
}