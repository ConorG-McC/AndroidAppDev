package com.example.androidappdev.presentation.screens.employees.edit

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.androidappdev.R
import com.example.androidappdev.data.employee.Employee
import com.example.androidappdev.presentation.components.BottomNavBar
import com.example.androidappdev.presentation.components.CustomButton
import com.example.androidappdev.presentation.components.CustomTextField

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun EditEmployeeScreen(modifier: Modifier = Modifier,
                       navController: NavHostController,
                       vm: EditEmployeeViewModel = viewModel(factory = EditEmployeeViewModel.Factory),
                       selectedEmployee: Employee,
                       onClickToHome: () -> Unit
) {
    val context = LocalContext.current
    LaunchedEffect(key1 = Unit) {
        vm.setSelectedEmployee(selectedEmployee)
    }

    Scaffold(modifier = modifier,
             bottomBar = { BottomNavBar(navController = navController) }) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(text = "Edit Employee",
                 textAlign = TextAlign.Center,
                 fontSize = 24.sp,
                 fontWeight = FontWeight.Bold,
                 color = Color.Black,
                 modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))
            Column(modifier = Modifier.fillMaxWidth(),
                   horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CustomTextField(hintText = stringResource(R.string.first_name_hint),
                                text = vm.firstName,
                                isPassword = false,
                                onNameChange = { vm.firstName = it },
                                errorMessage = stringResource(R.string.first_name_error_message),
                                errorPresent = vm.firstNameIsValid()
                )
                Spacer(modifier = Modifier.height(16.dp))
                CustomTextField(hintText = stringResource(R.string.surname_hint),
                                text = vm.surname,
                                isPassword = false,
                                onNameChange = { vm.surname = it },
                                errorMessage = stringResource(R.string.surname_error_message),
                                errorPresent = vm.surnameIsValid()
                )
                Spacer(modifier = Modifier.height(32.dp))
                CustomButton(text = stringResource(R.string.save),
                             clickButton = {
                                 if (vm.allDataIsValid()) {
                                     vm.updateEmployee()
                                     onClickToHome()
                                 } else {
                                     Toast.makeText(context,
                                                    R.string.fields_not_valid,
                                                    Toast.LENGTH_SHORT
                                     ).show()

                                 }
                             })
                Spacer(modifier = Modifier.height(16.dp))
                CustomButton(text = stringResource(R.string.close),
                             clickButton = { onClickToHome() })
            }
        }
    }
}
