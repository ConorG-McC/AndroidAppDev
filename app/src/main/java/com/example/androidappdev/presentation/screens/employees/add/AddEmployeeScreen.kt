package com.example.androidappdev.presentation.screens.employees.add

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.androidappdev.R
import com.example.androidappdev.presentation.components.CustomButton
import com.example.androidappdev.presentation.components.CustomTextField

@Composable
fun AddEmployeeScreen(text: String, modifier: Modifier = Modifier, navController: NavHostController, vm: EmployeeViewModel = viewModel(factory = EmployeeViewModel.Factory)) {
    val firstName: String by vm.firstName.observeAsState("")
    val surname: String by vm.surname.observeAsState("")
    val keyboardController = LocalSoftwareKeyboardController.current

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
        Column(modifier = Modifier.align(Alignment.CenterHorizontally)){
            CustomTextField(
                stringResource(R.string.first_name_hint),
                text = firstName,
                onNameChange = { vm.onFirstNameChange(it) },
                stringResource(R.string.first_name_error_message),
                !vm.firstName.value.isNullOrBlank()
            )
            CustomTextField(
                stringResource(R.string.surname_hint),
                text = surname,
                onNameChange = { vm.onSurnameChange(it) },
                stringResource(R.string.surname_error_message),
                !vm.surname.value.isNullOrBlank()
            )

            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
            ) {
                CustomButton(
                    stringResource(R.string.add),
                    clickButton = { vm.add()
                        keyboardController?.hide()
                        navController.navigate("Employees")
                    }
                )
            }

            Row( horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
            ){
                CustomButton(
                    stringResource(R.string.close),
                    clickButton = { navController.navigate("Employees") })
            }
        }
    }
}