package com.example.androidappdev.presentation.screens.employees.edit

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.androidappdev.R
import com.example.androidappdev.presentation.components.CustomButton
import com.example.androidappdev.presentation.components.CustomTextField

@Composable
fun EditEmployeeScreen(vm: EditEmployeeViewModel = viewModel(factory = EditEmployeeViewModel.Factory),
                       selectedContactIndex: Int,
                       onClickToHome: () -> Unit
) {

    vm.getEmployees(selectedContactIndex) //called each time component is updated

    Column(modifier = Modifier.fillMaxSize()
    ) {
        Text(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            text = "Edit",
            textAlign = TextAlign.Center,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
        )
        Column {
            CustomTextField(stringResource(R.string.first_name_hint),
                            text = vm.firstName,
                            onNameChange = { vm.firstName = it },
                            stringResource(R.string.first_name_error_message),
                            vm.firstNameIsValid()
            )

            CustomTextField(stringResource(R.string.surname_hint),
                            text = vm.surname,
                            onNameChange = { vm.surname = it },
                            stringResource(R.string.surname_error_message),
                            vm.surnameIsValid()
            )

            CustomButton(stringResource(R.string.edit), clickButton = {
                vm.updateEmployee()
                onClickToHome()
            })

        }
    }
}
