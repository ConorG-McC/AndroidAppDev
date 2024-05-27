package com.example.androidappdev.components

import androidx.compose.ui.test.hasClickAction
import androidx.compose.ui.test.hasContentDescription
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.performClick
import com.example.androidappdev.presentation.components.FloatingButton
import junit.framework.TestCase.assertTrue
import org.junit.Before
import org.junit.FixMethodOrder
import org.junit.Rule
import org.junit.Test
import org.junit.runners.MethodSorters

@FixMethodOrder(MethodSorters.DEFAULT)
class FloatingActionButtonTests {
    private val CONTENT_DESCRIPTION = "Add Button"
    private var state: Boolean = false

    private val button =
        hasContentDescription(CONTENT_DESCRIPTION) and hasClickAction()

    @get:Rule
    var rule = createComposeRule()

    @Before
    fun setUp() {
        rule.setContent {
            FloatingButton(contentDescription = CONTENT_DESCRIPTION,
                           clickAction = { state = true })
        }
    }

    @Test
    fun `Button executes function passed to it when clicked`() {
        rule.onNode(button).assertExists()
        rule.onNode(button).performClick()
        assertTrue(state)
    }
}