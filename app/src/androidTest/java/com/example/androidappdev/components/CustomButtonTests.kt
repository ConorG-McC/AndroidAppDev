package com.example.androidappdev.components

import androidx.compose.ui.test.hasClickAction
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.performClick
import com.example.androidappdev.presentation.components.CustomButton
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.FixMethodOrder
import org.junit.Rule
import org.junit.Test
import org.junit.runners.MethodSorters


/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
//fun CustomButton(text: String, clickButton: () -> Unit) {


@FixMethodOrder( MethodSorters.DEFAULT)
class CustomButtonTests {

    private val TEXT_DISPLAY = "button text"
    private var state: Boolean = false

    private val button = hasText(TEXT_DISPLAY) and hasClickAction()

    @get:Rule
    var rule = createComposeRule()

    @Before
    fun setUp() {
        rule.setContent {
            CustomButton(TEXT_DISPLAY, clickButton = {state=true})
        }
    }

    @Test
    fun `check if displays text and executes function passed to it when clicked`() {
        rule.onNode(button).assertExists()
        rule.onNode(button).performClick()
        assertTrue(state)
    }
}