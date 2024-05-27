package com.example.androidappdev.presentation.screens.login

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.androidappdev.R
import com.example.androidappdev.presentation.components.CustomButton
import com.example.androidappdev.presentation.components.CustomTextField
import com.example.androidappdev.presentation.screens.login.components.LogIn
import com.example.androidappdev.utils.Utils.Companion.showMessage

@SuppressLint("UnusedMaterialScaffoldPaddingParameter",
              "UnusedMaterial3ScaffoldPaddingParameter"
)
@Composable
fun LoginScreen(
        vm: LoginViewModel = viewModel(factory = LoginViewModel.Factory),
        navigateToSignUpScreen: () -> Unit,
        navigateToHomeScreen: () -> Unit,
) {
    val context = LocalContext.current
    val message: String by vm.message.observeAsState("")
    val keyboard = LocalSoftwareKeyboardController.current

    if (message.isNotEmpty()) {
        showMessage(context, message)
    }

    Scaffold(modifier = Modifier.fillMaxSize()
    ) {
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
               verticalArrangement = Arrangement.Center,
               horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Sign In",
                 textAlign = TextAlign.Center,
                 fontSize = 24.sp,
                 fontWeight = FontWeight.Bold,
                 modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))
            Column(modifier = Modifier.fillMaxWidth(),
                   horizontalAlignment = Alignment.CenterHorizontally,
                   verticalArrangement = Arrangement.Center
            ) {
                CustomTextField(
                    hintText = stringResource(R.string.email),
                    text = vm.email,
                    isPassword = false,
                    onNameChange = { vm.email = it },
                    errorMessage = stringResource(R.string.login_email_error_message),
                    errorPresent = vm.emailIsValid(),
                )
                Spacer(modifier = Modifier.height(16.dp))
                CustomTextField(
                    hintText = stringResource(R.string.password),
                    text = vm.password,
                    isPassword = true,
                    onNameChange = { vm.password = it },
                    errorMessage = stringResource(R.string.password_error_message),
                    errorPresent = vm.passwordIsValid(),
                )
                Spacer(modifier = Modifier.height(32.dp))
                CustomButton(text = stringResource(R.string.sign_in_button),
                             clickButton = {
                                 keyboard?.hide()
                                 vm.signInWithEmailAndPassword()
                             })
                Spacer(modifier = Modifier.height(16.dp))
                CustomButton(text = stringResource(R.string.forgot_password),
                             clickButton = {
                                 if (vm.emailIsValid()) {
                                     vm.forgotPassword()
                                 } else {
                                     showMessage(context,
                                                 "Valid email required to retrieve password"
                                     )
                                 }
                             })
                Spacer(modifier = Modifier.height(16.dp))
                CustomButton(text = stringResource(R.string.sign_up_button),
                             clickButton = navigateToSignUpScreen
                )
                Spacer(modifier = Modifier.height(16.dp))
                LogIn(vm = vm, showErrorMessage = { errorMessage ->
                    showMessage(context, errorMessage)
                }, navigateToHomeScreen = navigateToHomeScreen
                )
            }
        }
    }
}
