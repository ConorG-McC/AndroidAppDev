package com.example.androidappdev.presentation.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CustomTextField(hintText: String,
                    text: String,
                    isPassword: Boolean,
                    onNameChange: (String) -> Unit,
                    errorMessage: String,
                    errorPresent: Boolean
) {
    Surface(modifier = Modifier.padding(10.dp)) {
        OutlinedTextField(modifier = Modifier.semantics {
            contentDescription = hintText
        },
                          value = text,
                          onValueChange = onNameChange,
                          isError = errorPresent,
                          singleLine = true,
                          label = {
                              Text(hintText)
                          },
                          visualTransformation = if (isPassword) PasswordVisualTransformation(
                              '*'
                          )
                          else VisualTransformation.None
        )
        androidx.compose.material3.Text(
            modifier = Modifier
                .padding(10.dp)
                .testTag("Test".plus(hintText)),
            text = if (errorPresent) "" else errorMessage,
            fontSize = 14.sp,
            color = Color.Red,
        )
    }
}
