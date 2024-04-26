package com.example.androidappdev.presentation.screens.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.androidappdev.core.MyAppApplication
import com.example.androidappdev.data.auth.AuthRepo
import com.example.androidappdev.data.auth.SignInResponse
import com.example.androidappdev.data.database.Response
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch

class LoginViewModel(private val repo: AuthRepo) : ViewModel() {
    var email by mutableStateOf("")
    var passsword by mutableStateOf("")

    val isEmailVerified get() = repo.currentUser?.isEmailVerified ?: false
    var signInResponse by mutableStateOf<SignInResponse>(Response.Startup)
        private set

    private var _message = MutableLiveData(String())
    var message: LiveData<String> = _message
    fun emailIsValid(): Boolean {
        return email.isNotBlank()
    }

    fun passwordIsValid(): Boolean {
        return passsword.isNotBlank()
    }

    fun forgotPassword() {
        FirebaseAuth.getInstance().sendPasswordResetEmail(email)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    _message.value =
                        "Password reset email has been sentsuccessfully"
                } else {
                    _message.value = "Unable to send password reset email"
                }
            }
    }

    fun signInWithEmailAndPassword() = viewModelScope.launch {
        signInResponse = Response.Loading
        signInResponse =
            repo.firebaseSignInWithEmailAndPassword(email, passsword)
    }

    // Define ViewModel factory in a companion object
    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                LoginViewModel(repo = MyAppApplication.container.authRepository)
            }
        }
    }
}