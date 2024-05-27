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
    fun `default state of the home screen appears as expected`() {
        `sign in`(email = "newuser@email.com", password = "password")
        rule.onNode(homeScreenText).assertExists()

        rule.onNode(bottomNavBar).assertExists()
        rule.onNode(employeeNavBarItem).assertExists()
        rule.onNode(tasksNavBarItem).assertExists()
        rule.onNode(exitNavBarItem).assertExists()
    }

    @Test
    fun `logout`() {
        `sign in`(email = "newuser@email.com", password = "password")
        rule.onNode(exitNavBarItem).performClick()
    }

}