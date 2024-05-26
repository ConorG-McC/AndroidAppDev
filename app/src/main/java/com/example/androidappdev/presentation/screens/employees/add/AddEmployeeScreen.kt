package com.example.androidappdev.presentation.screens.employees.add

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.androidappdev.R
import com.example.androidappdev.presentation.components.BottomNavBar
import com.example.androidappdev.presentation.components.CustomButton
import com.example.androidappdev.presentation.components.CustomTextField

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AddEmployeeScreen(text: String,
                      modifier: Modifier = Modifier,
                      navController: NavHostController,
                      vm: AddEmployeeViewModel = viewModel(factory = AddEmployeeViewModel.Factory)
) {
    val context = LocalContext.current
    val firstName: String by vm.firstName.observeAsState("")
    val surname: String by vm.surname.observeAsState("")
    val keyboardController = LocalSoftwareKeyboardController.current

    Scaffold(bottomBar = { BottomNavBar(navController = navController) }) {
        Column(modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
               horizontalAlignment = Alignment.CenterHorizontally,
               verticalArrangement = Arrangement.Top
        ) {
            Text(text = text,
                 textAlign = TextAlign.Center,
                 fontSize = 24.sp,
                 fontWeight = FontWeight.Bold,
                 color = Color.Black,
                 modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))
            Column(modifier = Modifier.fillMaxWidth(),
                   horizontalAlignment = Alignment.CenterHorizontally,
                   verticalArrangement = Arrangement.Center
            ) {
                CustomTextField(hintText = stringResource(R.string.first_name_hint),
                                text = firstName,
                                isPassword = false,
                                onNameChange = { vm.onFirstNameChange(it) },
                                errorMessage = stringResource(R.string.first_name_error_message),
                                errorPresent = !vm.firstName.value.isNullOrBlank()
                )
                Spacer(modifier = Modifier.height(16.dp))
                CustomTextField(hintText = stringResource(R.string.surname_hint),
                                text = surname,
                                isPassword = false,
                                onNameChange = { vm.onSurnameChange(it) },
                                errorMessage = stringResource(R.string.surname_error_message),
                                errorPresent = !vm.surname.value.isNullOrBlank()
                )
                Spacer(modifier = Modifier.height(32.dp))
                Row(horizontalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    CustomButton(text = stringResource(R.string.add),
                                 clickButton = {
                                     if (vm.allDataIsValid()) {
                                         vm.add()
                                         keyboardController?.hide()
                                         navController.navigate("Employees")
                                     } else {
                                         Toast.makeText(context,
                                                        R.string.fields_not_valid,
                                                        Toast.LENGTH_SHORT
                                         ).show()
                                     }
                                 })
                }
                Spacer(modifier = Modifier.height(16.dp))
                Row(horizontalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    CustomButton(text = stringResource(R.string.close),
                                 clickButton = { navController.navigate("Employees") })
                }
            }
        }
    }
}
