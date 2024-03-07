package com.example.androidappdev.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.androidappdev.ContactViewModel
import com.example.androidappdev.R
import kotlin.system.exitProcess

@Composable
fun TasksScreen(
    text: String, modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        Text(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            text = text,
            textAlign = TextAlign.Center,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
        )
        OverallLayout()
    }
}


@Preview(showBackground = true)
@Composable
fun OverallLayout(vm: ContactViewModel = viewModel()) {
    val firstName: String by vm.firstName.observeAsState("")
    val surname: String by vm.surname.observeAsState("")
    val telNo: String by vm.telNo.observeAsState("")

    MaterialTheme {
        Column {
            TextField(
                stringResource(R.string.first_name_hint),
                text = firstName,
                onNameChange = { vm.onFirstNameChange(it) },
                stringResource(R.string.first_name_error_message),
                !vm.firstName.value.isNullOrBlank()
            )
            TextField(
                stringResource(R.string.surname_hint),
                text = surname,
                onNameChange = { vm.onSurnameChange(it) },
                stringResource(R.string.surname_error_message),
                !vm.surname.value.isNullOrBlank()
            )
            TextField(
                stringResource(R.string.tel_no_hint),
                text = telNo,
                onNameChange = {
                    vm.onTelNumberChange(it)
                },
                stringResource(R.string.tel_no_error_message),
                !vm.telNo.value.isNullOrBlank()
            )

            MyButton(stringResource(R.string.add),
                vm.allDataIsValid(),
                clickButton = { vm.add() })


            MyButton(stringResource(R.string.close),
                true,
                clickButton = { exitProcess(0) })
        }
    }
}

@Composable
fun TextField(
    hintText: String,
    text: String,
    onNameChange: (String) -> Unit,
    errorMessage: String,
    errorPresent: Boolean
) {
    Surface(
        modifier = Modifier.padding(10.dp)
    ) {
        OutlinedTextField(value = text,
            onValueChange = onNameChange,
            isError = errorPresent,
            singleLine = true,
            label = { Text(hintText) })
        Text(
            modifier = Modifier.padding(10.dp),
            text = if (errorPresent) "" else errorMessage,
            fontSize = 14.sp,
            color = Color.Red,
        )
    }
}

@Composable
fun MyButton(
    text: String,
    ifAllDataIsValid: Boolean,
    clickButton: () -> Unit
) {
    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxWidth()
    ) {
        Button(
            onClick = clickButton,
            enabled = ifAllDataIsValid,
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier.padding(horizontal = 10.dp)
        ) {
            Text(text = text, fontSize = 12.sp)
        }
    }
}