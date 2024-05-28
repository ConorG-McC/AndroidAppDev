package com.example.androidappdev.screens

import androidx.compose.ui.test.onNodeWithText
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

    @Test
    fun `Error messages are shown for incomplete text fields`() {
        `go to the Add Task screen`()

        var ERROR_MESSAGE_TEXT_TITLE = "Title is blank"
        var ERROR_MESSAGE_TEXT_DESCRIPTION = "Description is blank"

        rule.onNodeWithText(ERROR_MESSAGE_TEXT_TITLE).assertExists()
        rule.onNodeWithText(ERROR_MESSAGE_TEXT_DESCRIPTION).assertExists()
    }

    @Test
    fun `When a valid task is added or deleted the list should reflect appropriately`() {
        `go to the Add Task screen`()

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

    @Test
    fun `When an invalid task is attempted the user will not be returned to view task screen`() {
        `go to the Add Task screen`()

        //enter invalid data then submit
        `enter_an_invalid_task`()

        // Check that the screen is still the Add Task screen
        rule.onNode(taskTitleField).assertExists()

        // Check that the task has not been added
        rule.onNode(taskTitleField).equals("")
        rule.onNode(taskDescriptionField).equals("")

        // Check that the toast is shown
//        Thread.sleep(500)
//        rule.onNodeWithText(rule.activity.getString(R.string.fields_not_valid))
//            .assertExists()
    }

//    @Test
//    fun `When an invalid task is attempted a toast should be shown`() {
//        `go to the Add Task screen`()
//
//        //enter invalid data then submit
//        `enter_an_invalid_task`()
//
//        // Check that the toast is shown
//        Thread.sleep(500)
//        rule.onNodeWithText(rule.activity.getString(R.string.fields_not_valid))
//            .assertExists()
//    }

}