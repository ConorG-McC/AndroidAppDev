package com.example.androidappdev.screens

import androidx.compose.ui.test.performClick
import org.junit.Before
import org.junit.FixMethodOrder
import org.junit.Test
import org.junit.runners.MethodSorters

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class HomeScreenTests : ScreenTests() {
    @Before
    override fun setUp() {
        super.setUp()
    }

    @Test
    fun `check default state of the home screen`() {
        `sign in`(email = "newuser@email.com", password = "password")
        rule.onNode(homeScreenText).assertExists()

        rule.onNode(bottomNavBar).assertExists()
        rule.onNode(employeeNavBarItem).assertExists()
        rule.onNode(tasksNavBarItem).assertExists()
        rule.onNode(exitNavBarItem).assertExists()
    }

    @Test
    fun `go to the employee screen`() {
        `sign in`(email = "newuser@email.com", password = "password")
        rule.onNode(employeeNavBarItem).performClick()
    }

    @Test
    fun `logout`() {
        `sign in`(email = "newuser@email.com", password = "password")
        rule.onNode(exitNavBarItem).performClick()
    }

//    @Test
//    fun `go to edit screen`() {
//        `sign in`(email = "newuser@email.com", password = "password")
//        //Add a contact to view
//        rule.onNode(addNavBarItem).performClick()
//        `enter_a_valid_user`()
//        //select and edit the contact
//        rule.onNode(listItem).performClick()
//        //Check on the edit screen
//        rule.onNode(editScreenText).assertExists()
//
//        rule.onNode(homeNavBarItem).performClick()
//        // Tidy up - Delete the contact
//        rule.onNode(listItem).performClick()
//        rule.onNode(deleteButton).performClick()
//    }
}