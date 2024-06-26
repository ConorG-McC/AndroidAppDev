package com.example.androidappdev.presentation.screens.employees.add

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.androidappdev.core.MyAppApplication
import com.example.androidappdev.data.auth.AuthRepo
import com.example.androidappdev.data.employee.Employee
import com.example.androidappdev.data.employee.EmployeeRepo

class AddEmployeeViewModel(private val repo: EmployeeRepo,
                           private val authRepo: AuthRepo
) : ViewModel() {

    private var _firstName = MutableLiveData(String())
    val firstName: LiveData<String> = _firstName
    fun onFirstNameChange(firstName: String) {
        _firstName.value = firstName
    }

    private var _surname = MutableLiveData(String())
    val surname: LiveData<String> = _surname
    fun onSurnameChange(description: String) {
        _surname.value = description
    }

    fun allDataIsValid(): Boolean {
        return _firstName.value!!.isNotBlank() && _surname.value!!.isNotBlank()
    }

    fun add() {
        if (allDataIsValid()) {
            val newEmployee =
                Employee(_firstName.value.toString(), _surname.value.toString()
                )
            Log.d("NEW EMPLOYEE TO ADD", newEmployee.toString())
            repo.addEmployee(newEmployee, authRepo.currentUser!!.uid)
            clear()
        }
    }

    private fun clear() {
        _firstName.value = String()
        _surname.value = String()
    }

    //     Define ViewModel factory in a companion object
    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                AddEmployeeViewModel(authRepo = MyAppApplication.container.authRepository,
                                     repo = MyAppApplication.container.employeeRepository
                )
            }
        }

    }
}
