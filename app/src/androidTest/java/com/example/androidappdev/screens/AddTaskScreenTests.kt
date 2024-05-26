package com.example.androidappdev.screens

import androidx.compose.ui.test.performClick
import org.junit.Before
import org.junit.FixMethodOrder
import org.junit.Test
import org.junit.runners.MethodSorters

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class AddTaskScreenTests : ScreenTests() {
    @Before
    override fun setUp() {
        super.setUp()
    }

    private fun `go to the Add Task screen`() {
        `sign in`(email = "newuser@email.com", password = "password")
        rule.onNode(tasksNavBarItem).performClick()
        rule.onNode(tasksFloatingActionButton).performClick()
    }

    @Test
    fun `default state of the view task screen appears as expected`() {
        `go to the Add Task screen`()

        rule.onNode(taskTitleField).assertExists()
        rule.onNode(taskDescriptionField).assertExists()
        rule.onNode(taskStatusDropdown).assertExists()

        rule.onNode(bottomNavBar).assertExists()
        rule.onNode(employeeNavBarItem).assertExists()
        rule.onNode(tasksNavBarItem).assertExists()
        rule.onNode(exitNavBarItem).assertExists()
    }


}