package com.example.androidappdev.screens

import androidx.compose.ui.test.SemanticsMatcher
import androidx.compose.ui.test.hasAnySibling
import androidx.compose.ui.test.hasContentDescription
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import com.example.androidappdev.R
import com.example.androidappdev.core.MainActivity
import com.example.androidappdev.data.task.TaskStatus
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

    //For home screen
    lateinit var homeScreenText: SemanticsMatcher

    //For Tasks screen
    lateinit var tasksFloatingActionButton: SemanticsMatcher
    lateinit var tasksScreenTitleText: SemanticsMatcher
    lateinit var tasksScreenEmptyText: SemanticsMatcher
    lateinit var addButton: SemanticsMatcher
    lateinit var editButton: SemanticsMatcher
    lateinit var deleteButton: SemanticsMatcher

    //For add screen
    val TASK_TITLE1 = "task1"
    val TASK_DESCRIPTION1 = "description1"
    val TASK_STATUS1 = TaskStatus.IN_PROGRESS
    lateinit var taskTitleField: SemanticsMatcher
    lateinit var taskDescriptionField: SemanticsMatcher
    lateinit var taskStatusDropdown: SemanticsMatcher

    val listItem =
        hasText("$TASK_TITLE1 $TASK_DESCRIPTION1 ${TASK_STATUS1.status}")

    //Data for add screen
//    val FIRST_NAME1 = "first1"
//    val SURNAME1 = "surname1"
//    val TELNO1 = "telNo1"
//
//    lateinit var telephoneNumberTextField: SemanticsMatcher
//    lateinit var addScreenText: SemanticsMatcher


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

        //Home screen
        homeScreenText =
            hasText(rule.activity.getString(R.string.home_welcome_message))

        //Tasks screen
        tasksScreenEmptyText =
            hasText(rule.activity.getString(R.string.task_screen_default_message))
        tasksFloatingActionButton = hasContentDescription("Add new task")
        taskTitleField =
            hasContentDescription(rule.activity.getString(R.string.title_hint))
        taskDescriptionField =
            hasContentDescription(rule.activity.getString(R.string.description_hint))
        taskStatusDropdown = hasContentDescription("StatusDropdown")
        addButton =
            hasContentDescription(rule.activity.getString(R.string.add) + " button")

        editButton =
            hasContentDescription(rule.activity.getString(R.string.edit) + " button")
        deleteButton =
            hasContentDescription(rule.activity.getString(R.string.delete) + BUTTON_POSTFIX)


//
//
//        addScreenText =
//            hasText(rule.activity.getString(R.string.add)) and hasNoClickAction()
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
    fun `enter_a_valid_task`() {
        rule.onNode(taskTitleField).performTextInput(TASK_TITLE1)
        rule.onNode(taskDescriptionField).performTextInput(TASK_DESCRIPTION1)
        // Open the dropdown menu and select a status
        rule.onNode(taskStatusDropdown).performClick()
        rule.onNodeWithText(TASK_STATUS1.status).performClick()

        rule.onNode(addButton).performClick()
    }

    //enter an invalid task
    fun `enter_an_invalid_task`() {
        rule.onNode(taskTitleField).performTextInput("")
        rule.onNode(taskDescriptionField).performTextInput("")
        // Open the dropdown menu and select a status
        rule.onNode(taskStatusDropdown).performClick()
        rule.onNodeWithText(TASK_STATUS1.status).performClick()

        rule.onNode(addButton).performClick()
    }
}