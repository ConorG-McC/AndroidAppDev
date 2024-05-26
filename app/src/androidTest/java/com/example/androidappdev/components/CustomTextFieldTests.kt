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

@FixMethodOrder( MethodSorters.DEFAULT)
class CustomTextFieldTests {
    //Data to initialise component
    private val HINT_TEXT = "hint text"
    private val TEXT_TO_BE_DISPLAYED = "text"
    private var textInput = ""
    private val ERROR_MESSAGE_TEXT = "error displayed"
    private var errorIsNotPresent = true

    //Screen elements to test
    private val textToEnter = hasText(TEXT_TO_BE_DISPLAYED)
    private val hintText = hasText(HINT_TEXT)
    private val errorMessageText = hasText(ERROR_MESSAGE_TEXT)

    @get:Rule
    var rule = createComposeRule()

    @Test
    fun `Textfield displays expected default state while text is present`() {
        rule.setContent {
            AndroidAppDevTheme {
                CustomTextField(
                    HINT_TEXT,
                    TEXT_TO_BE_DISPLAYED,
                    isPassword = false,
                    onNameChange = { textInput = it },
                    ERROR_MESSAGE_TEXT,
                    errorIsNotPresent
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
                CustomTextField(
                    HINT_TEXT,
                    TEXT_TO_BE_DISPLAYED,
                    isPassword =  false,
                    onNameChange = { textInput = it },
                    ERROR_MESSAGE_TEXT,
                    errorIsNotPresent
                )
            }
        }
        val ADDITIONAL_TEXT = "something"
        rule.onNode(hintText).assertExists()
        rule.onNode(textToEnter).performTextInput(ADDITIONAL_TEXT)

        rule.onNode(errorMessageText).assertDoesNotExist()
        TestCase.assertTrue(errorIsNotPresent)
        //Cursor is not at the end and setting the mouse cursor as such requires modification to onValueChange
        TestCase.assertEquals(textInput, ADDITIONAL_TEXT.plus(TEXT_TO_BE_DISPLAYED))
    }

    @Test
    fun `textfield displays error_message when error is present`() {
        //As validation of button is external and this component is tested separately we need to set the errorIsNotPresent to false explicitly
        //Text - regardless of contents - is irrelevant to the validation in the context of this component
        rule.setContent {
            AndroidAppDevTheme {
                CustomTextField(
                    HINT_TEXT,
                    "",
                    isPassword = false,
                    onNameChange = { textInput = it },
                    ERROR_MESSAGE_TEXT,
                    !errorIsNotPresent
                )
            }
        }
        rule.onNodeWithText("").assertExists()
        rule.onNode(errorMessageText).assertExists()
    }

    @Test
    fun `Textfield displays asterix of characters when set to password`() {

        var inputText = "test"

        rule.setContent {
            AndroidAppDevTheme {
                CustomTextField(
                    HINT_TEXT,
                    inputText,
                    isPassword = true,
                    onNameChange = { textInput = it },
                    ERROR_MESSAGE_TEXT,
                    !errorIsNotPresent
                )
            }
        }
        //Replace contents with *
        inputText = inputText.replaceRange(0,inputText.length,"*")
        rule.onNodeWithText("****").assertExists()
    }
}