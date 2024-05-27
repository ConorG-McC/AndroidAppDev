package com.example.androidappdev.screens

import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import org.junit.Before
import org.junit.FixMethodOrder
import org.junit.Test
import org.junit.runners.MethodSorters

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class ViewTasksScreenTests : ScreenTests() {
    @Before
    override fun setUp() {
        super.setUp()
    }

    private fun `go to the Tasks screen`() {
        `sign in`(email = "newuser@email.com", password = "password")
        rule.onNode(tasksNavBarItem).performClick()
    }

    @Test
    fun `default state of the view task screen appears as expected`() {
        `go to the Tasks screen`()

        rule.onNode(tasksScreenEmptyText).assertExists()

        rule.onNode(bottomNavBar).assertExists()
        rule.onNode(employeeNavBarItem).assertExists()
        rule.onNode(tasksNavBarItem).assertExists()
        rule.onNode(exitNavBarItem).assertExists()
    }

    // test that when a task is added it now appears on the screen
    @Test
    fun `When a valid task is added the list should show this task appropriately`() {
        `go to the Tasks screen`()

        rule.onNode(tasksFloatingActionButton).performClick()

        //enter valid data then submit
        `enter_a_valid_task`()

        // Check entry exists in list view
        rule.onNodeWithText(TASK_TITLE1).assertExists()
        rule.onNodeWithText(TASK_DESCRIPTION1).assertExists()
        rule.onNodeWithText(TASK_STATUS1.status).assertExists()

        //delete entry
        rule.onNodeWithText(TASK_TITLE1).performClick()
        rule.onNode(deleteButton).performClick()

        //check that the task has been deleted
        rule.onNode(tasksScreenEmptyText).assertExists()

    }

    // when a task is deleted it should no longer appear on the screen
    @Test
    fun `When a valid task is deleted the list should reflect appropriately`() {
        `go to the Tasks screen`()

        rule.onNode(tasksFloatingActionButton).performClick()

        //enter valid data then submit
        `enter_a_valid_task`()

        // Check entry exists in list view
        rule.onNodeWithText(TASK_TITLE1).assertExists()
        rule.onNodeWithText(TASK_DESCRIPTION1).assertExists()
        rule.onNodeWithText(TASK_STATUS1.status).assertExists()

        //delete entry
        rule.onNodeWithText(TASK_TITLE1).performClick()
        rule.onNode(deleteButton).performClick()

        //check that the task has been deleted
        rule.onNode(tasksScreenEmptyText).assertExists()

    }

}