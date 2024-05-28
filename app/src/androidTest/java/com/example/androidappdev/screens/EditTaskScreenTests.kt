package com.example.androidappdev.screens

import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import org.junit.Before
import org.junit.FixMethodOrder
import org.junit.Test
import org.junit.runners.MethodSorters

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class EditTaskScreenTests : ScreenTests() {
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
    fun `default state of the edit task screen appears as expected`() {
        `go to the Add Task screen`()

        //enter valid data then submit
        `enter_a_valid_task`()

        // Check entry exists in list view
        rule.onNodeWithText(TASK_TITLE1).assertExists()
        rule.onNodeWithText(TASK_DESCRIPTION1).assertExists()
        rule.onNodeWithText(TASK_STATUS1.status).assertExists()

        //click on the task and click edit button
        rule.onNodeWithText(TASK_TITLE1).performClick()
        rule.onNode(editButton).performClick()

        rule.onNode(taskTitleField).assertExists()
        rule.onNode(taskDescriptionField).assertExists()
        rule.onNode(taskStatusDropdown).assertExists()

        rule.onNode(bottomNavBar).assertExists()
        rule.onNode(employeeNavBarItem).assertExists()
        rule.onNode(tasksNavBarItem).assertExists()
        rule.onNode(exitNavBarItem).assertExists()

        // Close the edit screen
        rule.onNode(closeButton).performClick()

        // Delete the task
        rule.onNodeWithText(TASK_TITLE1).performClick()
        rule.onNode(deleteButton).performClick()

        // Check that the task has been deleted
        rule.onNode(tasksScreenEmptyText).assertExists()
    }

    @Test
    fun `When a valid task is edited in correct format the list reflects this appropriately`() {
        `go to the Add Task screen`()

        //enter valid data then submit
        `enter_a_valid_task`()

        // Check entry exists in list view
        rule.onNodeWithText(TASK_TITLE1).assertExists()
        rule.onNodeWithText(TASK_DESCRIPTION1).assertExists()
        rule.onNodeWithText(TASK_STATUS1.status).assertExists()

        //click on the task and click edit button
        rule.onNodeWithText(TASK_TITLE1).performClick()
        rule.onNode(editButton).performClick()

        `edit_selected_task_with_valid_details`()

        // Check entry exists in list view
        rule.onNodeWithText(TASK_TITLE2).assertExists()
        rule.onNodeWithText(TASK_DESCRIPTION2).assertExists()
        rule.onNodeWithText(TASK_STATUS2.status).assertExists()

        //delete entry
        rule.onNodeWithText(TASK_TITLE2).performClick()
        rule.onNode(deleteButton).performClick()

        //check that the task has been deleted
        rule.onNode(tasksScreenEmptyText).assertExists()

    }

    @Test
    fun `When a valid task is edited with incorrect format the user remains on edit screen`() {
        `go to the Add Task screen`()

        //enter valid data then submit
        `enter_a_valid_task`()

        // Check entry exists in list view
        rule.onNodeWithText(TASK_TITLE1).assertExists()
        rule.onNodeWithText(TASK_DESCRIPTION1).assertExists()
        rule.onNodeWithText(TASK_STATUS1.status).assertExists()

        //click on the task and click edit button
        rule.onNodeWithText(TASK_TITLE1).performClick()
        rule.onNode(editButton).performClick()

        `edit_selected_task_with_invalid_details`()

        // Check that the toast is shown
//        Thread.sleep(500)
//        rule.onNodeWithText(rule.activity.getString(R.string.fields_not_valid))
//            .assertExists()

        // Check that the screen is still the Add Task screen
        rule.onNode(taskTitleField).assertExists()

        // Close the edit screen
        rule.onNode(closeButton).performClick()

        // Delete the task
        rule.onNodeWithText(TASK_TITLE1).performClick()
        rule.onNode(deleteButton).performClick()

        // Check that the task has been deleted
        rule.onNode(tasksScreenEmptyText).assertExists()
    }

    @Test
    fun `Error messages are shown for incomplete text fields`() {
        `go to the Add Task screen`()

        //enter valid data then submit
        `enter_a_valid_task`()

        // Check entry exists in list view
        rule.onNodeWithText(TASK_TITLE1).assertExists()
        rule.onNodeWithText(TASK_DESCRIPTION1).assertExists()
        rule.onNodeWithText(TASK_STATUS1.status).assertExists()

        //click on the task and click edit button
        rule.onNodeWithText(TASK_TITLE1).performClick()
        rule.onNode(editButton).performClick()

        `edit_selected_task_with_invalid_details`()

        // Check that the screen is still the Add Task screen
        rule.onNode(taskTitleField).assertExists()

        var ERROR_MESSAGE_TEXT_TITLE = "Title is blank"
        var ERROR_MESSAGE_TEXT_DESCRIPTION = "Description is blank"

        rule.onNodeWithText(ERROR_MESSAGE_TEXT_TITLE).assertExists()
        rule.onNodeWithText(ERROR_MESSAGE_TEXT_DESCRIPTION).assertExists()

        // Close the edit screen
        rule.onNode(closeButton).performClick()

        // Delete the task
        rule.onNodeWithText(TASK_TITLE1).performClick()
        rule.onNode(deleteButton).performClick()

        // Check that the task has been deleted
        rule.onNode(tasksScreenEmptyText).assertExists()
    }

    @Test
    fun `When close button is clicked the task remains unchanged`() {
        `go to the Add Task screen`()

        //enter valid data then submit
        `enter_a_valid_task`()

        // Check entry exists in list view
        rule.onNodeWithText(TASK_TITLE1).assertExists()
        rule.onNodeWithText(TASK_DESCRIPTION1).assertExists()
        rule.onNodeWithText(TASK_STATUS1.status).assertExists()

        //click on the task and click edit button
        rule.onNodeWithText(TASK_TITLE1).performClick()
        rule.onNode(editButton).performClick()

        // Close the edit screen
        rule.onNode(closeButton).performClick()

        // Check entry exists in list view and is unchanged
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