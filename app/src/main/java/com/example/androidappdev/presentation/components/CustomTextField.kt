package com.example.androidappdev.presentation.components

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CustomTextField(hintText: String,
              text: String,
              onNameChange: (String) -> Unit,
              errorMessage: String,
              errorPresent: Boolean){


    androidx.compose.material3.Surface(modifier = Modifier.padding(10.dp)) {
        androidx.compose.material3.OutlinedTextField(
            value = text,
            onValueChange = onNameChange,
            isError = errorPresent,
            singleLine = true,
            label = {
                androidx.compose.material3.Text(hintText)
            }
        )
        androidx.compose.material3.Text(
            modifier = Modifier
                .padding(10.dp)
                .testTag("Test".plus(hintText)),
            text =  if (errorPresent) "" else errorMessage,
            fontSize = 14.sp,
            color = Color.Red,
        )
    }
}
