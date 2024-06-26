package com.example.androidappdev.components

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.navigation.compose.rememberNavController
import com.example.androidappdev.presentation.components.BottomNavBar
import com.example.androidappdev.presentation.theme.AndroidAppDevTheme
import org.junit.Before
import org.junit.FixMethodOrder
import org.junit.Rule
import org.junit.Test
import org.junit.runners.MethodSorters

@FixMethodOrder(MethodSorters.DEFAULT)
class BottomNavBarTests {

    @get:Rule
    val rule = createComposeRule()

    @Before
    fun setUp() {
        rule.setContent {
            val navController = rememberNavController()
            AndroidAppDevTheme {
                BottomNavBar(navController = navController)
            }
        }
    }

    @Test
    fun `bottom nav bar displays all expected items`() {

        rule.onNodeWithContentDescription("bottom_nav").assertExists()
        rule.onNodeWithContentDescription("nav Home").assertExists()
        rule.onNodeWithContentDescription("nav Employees").assertExists()
        rule.onNodeWithContentDescription("nav Tasks").assertExists()
        rule.onNodeWithContentDescription("nav Logout").assertExists()

    }

    @Test
    fun `bottom nav bar displays correct labels`() {
        rule.onNodeWithText("Home").assertExists()
        rule.onNodeWithText("Employees").assertExists()
        rule.onNodeWithText("Tasks").assertExists()
        rule.onNodeWithText("Logout").assertExists()
    }
}
