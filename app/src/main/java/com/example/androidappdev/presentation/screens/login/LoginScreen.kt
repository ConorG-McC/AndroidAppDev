package com.example.androidappdev.presentation.screens.login

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.androidappdev.R
import com.example.androidappdev.presentation.components.CustomButton
import com.example.androidappdev.presentation.components.CustomTextField
import com.example.androidappdev.presentation.components.SmallSpacer
import com.example.androidappdev.presentation.screens.login.components.LogIn
import com.example.androidappdev.utils.Utils.Companion.showMessage

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun LoginScreen(
        vm: LoginViewModel = viewModel(factory = LoginViewModel.Factory),
        navigateToSignUpScreen: () -> Unit,
        navigateToHomeScreen: () -> Unit,
) {
    val context = LocalContext.current
    val message: String by vm.message.observeAsState(String())
    val keyboard = LocalSoftwareKeyboardController.current

    if (message.isNotEmpty()) {
        //Only changes when vm message is updated
        showMessage(context, vm.message.value)
    }


    Scaffold(
        modifier = Modifier.fillMaxSize(),
    ) {
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
               verticalArrangement = Arrangement.Top,
               horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CustomTextField(
                hintText = stringResource(R.string.email),
                text = vm.email,
                isPassword = false,
                onNameChange = { vm.email = it },
                stringResource(R.string.login_email_error_message),
                vm.emailIsValid(),
            )
            CustomTextField(
                hintText = stringResource(R.string.password),
                text = vm.passsword,
                isPassword = true,
                onNameChange = { vm.passsword = it },
                stringResource(R.string.password_error_message),
                vm.passwordIsValid(),
            )

            SmallSpacer()
            CustomButton(text = stringResource(R.string.sign_in_button),
                         clickButton = {
                             keyboard?.hide()
                             vm.signInWithEmailAndPassword()
                         })
            SmallSpacer()
            Row(modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                CustomButton(stringResource(R.string.forgot_password),
                             clickButton = {
                                 if (vm.emailIsValid()) {
                                     vm.forgotPassword()
                                 } else {
                                     showMessage(context,
                                                 "valid email to retrievepassword"
                                     )
                                 }
                             })
                SmallSpacer()
                CustomButton(text = stringResource(R.string.sign_up_button),
                             clickButton = {
                                 navigateToSignUpScreen()
                             })
            }

            LogIn(vm = vm, showErrorMessage = { errorMessage ->
                showMessage(context, errorMessage)
            }, navigateToHomeScreen = navigateToHomeScreen
            )
        }
    }
}
