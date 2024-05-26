package com.example.androidappdev.components

import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performTextInput
import com.example.androidappdev.presentation.components.CustomTextField
import com.example.androidappdev.presentation.theme.AndroidAppDevTheme
import junit.framework.TestCase
import org.junit.FixMethodOrder
import org.junit.Rule
import org.junit.Test
import org.junit.runners.MethodSorters

@FixMethodOrder(MethodSorters.DEFAULT)
class CustomTextFieldTests {
    // Data to initialize component
    private val HINT_TEXT = "hint text"
    private val TEXT_TO_BE_DISPLAYED = "text"
    private var textInput = TEXT_TO_BE_DISPLAYED
    private val ERROR_MESSAGE_TEXT = "error displayed"
    private var errorIsNotPresent = true

    // Screen elements to test
    private val textToEnter = hasText(TEXT_TO_BE_DISPLAYED)
    private val hintText = hasText(HINT_TEXT)
    private val errorMessageText = hasText(ERROR_MESSAGE_TEXT)

    @get:Rule
    var rule = createComposeRule()

    @Test
    fun `Textfield displays expected default state while text is present`() {
        rule.setContent {
            AndroidAppDevTheme {
                CustomTextField(hintText = HINT_TEXT,
                                text = TEXT_TO_BE_DISPLAYED,
                                isPassword = false,
                                onNameChange = { textInput = it },
                                errorMessage = ERROR_MESSAGE_TEXT,
                                errorPresent = errorIsNotPresent
                )
            }
        }

        rule.onNode(hintText).assertExists()
        rule.onNode(textToEnter).assertExists()

        rule.onNodeWithText(ERROR_MESSAGE_TEXT).assertDoesNotExist()
        TestCase.assertTrue(errorIsNotPresent)
    }

    @Test
    fun `Textfield displays expected state after additional valid text is input`() {
        rule.setContent {
            AndroidAppDevTheme {
                CustomTextField(hintText = HINT_TEXT,
                                text = textInput,
                                isPassword = false,
                                onNameChange = { textInput = it },
                                errorMessage = ERROR_MESSAGE_TEXT,
                                errorPresent = errorIsNotPresent
                )
            }
        }
        val ADDITIONAL_TEXT = "something"
        rule.onNode(hintText).assertExists()
        rule.onNode(textToEnter).performTextInput(ADDITIONAL_TEXT)

        // Give time for Compose to reflect state updates
        rule.waitForIdle()

        // Log the textInput state for debugging
        println("Current textInput value: $textInput")

        rule.onNodeWithText(textInput)
            .equals(TEXT_TO_BE_DISPLAYED.plus(ADDITIONAL_TEXT))

    }

    @Test
    fun `textfield displays error_message when error is present`() {
        rule.setContent {
            AndroidAppDevTheme {
                CustomTextField(hintText = HINT_TEXT,
                                text = "",
                                isPassword = false,
                                onNameChange = { textInput = it },
                                errorMessage = ERROR_MESSAGE_TEXT,
                                errorPresent = !errorIsNotPresent
                )
            }
        }
        rule.onNodeWithText("").assertExists()
        rule.onNode(errorMessageText).assertExists()
    }

    @Test
    fun `Textfield displays asterisks of characters when set to password`() {
        var inputText = "test"

        rule.setContent {
            AndroidAppDevTheme {
                CustomTextField(hintText = HINT_TEXT,
                                text = inputText,
                                isPassword = true,
                                onNameChange = { textInput = it },
                                errorMessage = ERROR_MESSAGE_TEXT,
                                errorPresent = !errorIsNotPresent
                )
            }
        }
        // Replace contents with *
        inputText = inputText.replaceRange(0, inputText.length, "*")
        rule.onNodeWithText("****").assertExists()
    }
}
