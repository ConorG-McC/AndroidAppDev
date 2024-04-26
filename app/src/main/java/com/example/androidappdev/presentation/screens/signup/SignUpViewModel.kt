package com.example.androidappdev.presentation.screens.signup

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.androidappdev.core.MyAppApplication
import com.example.androidappdev.data.auth.AuthRepo
import com.example.androidappdev.data.auth.SendEmailVerificationResponse
import com.example.androidappdev.data.auth.SignUpResponse
import com.example.androidappdev.data.database.Response
import com.example.androidappdev.data.user.User
import com.example.androidappdev.data.user.UserRepo

import kotlinx.coroutines.launch

class SignUpViewModel(private val repo: AuthRepo, private val userRepo: UserRepo
) : ViewModel() {
    var firstName by mutableStateOf("")
    var surname by mutableStateOf("")
    var email by mutableStateOf("")
    var password by mutableStateOf("")
    fun firstNameIsValid(): Boolean {
        return firstName.isNotBlank()
    }

    fun surnameIsValid(): Boolean {
        return surname.isNotBlank()
    }

    fun emailIsValid(): Boolean {
        return email.isNotBlank()
    }

    fun passwordIsValid(): Boolean {
        return password.isNotBlank()
    }

    var signUpResponse by mutableStateOf<SignUpResponse>(Response.Success(false))
        private set

    var sendEmailVerificationResponse by mutableStateOf<SendEmailVerificationResponse>(
        Response.Success(false)
    )
        private set

    fun signUpWithEmailAndPassword() = viewModelScope.launch {
        signUpResponse = Response.Loading
        signUpResponse =
            repo.firebaseSignUpWithEmailAndPassword(email, password)
    }

    fun sendEmailVerification() = viewModelScope.launch {
        sendEmailVerificationResponse = Response.Loading
        sendEmailVerificationResponse = repo.sendEmailVerification()
        //User's details have been accepted
        val user = User(firstName, surname)
        userRepo.add(user, repo.currentUser!!.uid)
    }

    // Define ViewModel factory in a companion object
    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                SignUpViewModel(repo = MyAppApplication.container.authRepository,
                                userRepo = MyAppApplication.container.userRepository
                )
            }
        }
    }
}