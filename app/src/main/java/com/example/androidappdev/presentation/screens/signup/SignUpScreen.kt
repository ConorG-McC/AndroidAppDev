package com.example.androidappdev.presentation.screens.signup

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.androidappdev.R
import com.example.androidappdev.presentation.components.CustomTextField
import com.example.androidappdev.presentation.components.SmallSpacer
import com.example.androidappdev.presentation.screens.signup.components.SignUp
import com.example.androidappdev.utils.Utils.Companion.showMessage

@Composable
fun SignUpScreen(vm: SignUpViewModel = viewModel(factory = SignUpViewModel.Factory),
                 navigateBack: () -> Unit
) {
    val context = LocalContext.current

    Scaffold(content = { padding ->
        val keyboard = LocalSoftwareKeyboardController.current

        Column(modifier = Modifier
            .fillMaxSize()
            .padding(padding),
               verticalArrangement = Arrangement.Center,
               horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CustomTextField(hintText = stringResource(R.string.first_name_hint),
                            text = vm.firstName,
                            isPassword = false,
                            onNameChange = { vm.firstName = it },
                            errorMessage = stringResource(R.string.first_name_error_message),
                            errorPresent = vm.firstNameIsValid()
            )
            SmallSpacer()
            CustomTextField(stringResource(R.string.surname_hint),
                            text = vm.surname,
                            isPassword = false,
                            onNameChange = { vm.surname = it },
                            stringResource(R.string.surname_error_message),
                            errorPresent = vm.surnameIsValid()
            )
            SmallSpacer()
            CustomTextField(stringResource(R.string.email),
                            text = vm.email,
                            isPassword = false,
                            onNameChange = { vm.email = it },
                            stringResource(R.string.login_email_error_message),
                            errorPresent = vm.emailIsValid()
            )
            SmallSpacer()
            CustomTextField(stringResource(R.string.password),
                            text = vm.password,
                            isPassword = true,
                            onNameChange = { vm.password = it },
                            stringResource(R.string.password_error_message),
                            errorPresent = vm.passwordIsValid()
            )
            SmallSpacer()
            Button(onClick = {
                keyboard?.hide()
                vm.signUpWithEmailAndPassword()
            }) {
                Text(text = stringResource(R.string.sign_up_button),
                     fontSize = 15.sp
                )
            }
            Row {
                Button(onClick = {
                    navigateBack()
                }) {
                    Text(text = stringResource(R.string.already_a_user),
                         fontSize = 15.sp
                    )
                }

            }
        }
    })

    SignUp(vm = vm,
           sendEmailVerification = { vm.sendEmailVerification() },
           showVerifyEmailMessage = {
               showMessage(context, "Confirm details via email")
           },
           showFailureToSignUpMessage = {
               showMessage(context,
                           "Unable to create sign up due to permissions"
               )
           })
}
