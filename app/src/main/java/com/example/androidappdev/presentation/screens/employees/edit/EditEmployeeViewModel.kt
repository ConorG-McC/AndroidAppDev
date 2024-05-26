package com.example.androidappdev.presentation.screens.employees.edit

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.androidappdev.core.MyAppApplication
import com.example.androidappdev.data.auth.AuthRepo
import com.example.androidappdev.data.employee.Employee
import com.example.androidappdev.data.employee.EmployeeRepo

class EditEmployeeViewModel(private val repo: EmployeeRepo,
                            private val authRepo: AuthRepo
) : ViewModel() {
    private var selectedEmployee: Employee? = null

    var id by mutableStateOf("")
    var firstName by mutableStateOf("")
    var surname by mutableStateOf("")
    fun setSelectedEmployee(employee: Employee) {
        id = employee.id.toString()
        firstName = employee.firstName.toString()
        surname = employee.surname.toString()
        selectedEmployee = employee
    }

    fun firstNameIsValid(): Boolean {
        return firstName.isNotBlank()
    }

    fun surnameIsValid(): Boolean {
        return surname.isNotBlank()
    }

    fun updateEmployee() {
        selectedEmployee!!.firstName = firstName
        selectedEmployee!!.surname = surname
        repo.editEmployee(selectedEmployee!!, authRepo.currentUser!!.uid)
    }

    fun allDataIsValid(): Boolean {
        return firstName.isNotBlank() && surname.isNotBlank()
    }

    // Define ViewModel factory in a companion object
    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                EditEmployeeViewModel(authRepo = MyAppApplication.container.authRepository,
                                      repo = MyAppApplication.container.employeeRepository
                )
            }
        }
    }
}
