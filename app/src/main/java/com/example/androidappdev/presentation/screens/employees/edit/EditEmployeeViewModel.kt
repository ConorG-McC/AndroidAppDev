package com.example.androidappdev.presentation.screens.employees.edit

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.androidappdev.core.MyAppApplication
import com.example.androidappdev.data.`object`.Employee
import com.example.androidappdev.data.repositories.EmployeeInMemoryRepository

class EditEmployeeViewModel(private val repo: EmployeeInMemoryRepository) :
    ViewModel() {
    private var selectedEmployee: Employee? = null

    var firstName by mutableStateOf("")
    var surname by mutableStateOf("")
    fun firstNameIsValid(): Boolean {
        return firstName.isNotBlank()
    }

    fun surnameIsValid(): Boolean {
        return surname.isNotBlank()
    }

    fun getEmployees(selectedIndex: Int) {//Display when screen loads
        if (selectedEmployee == null) {
            selectedEmployee = repo.getAllEmployee()[selectedIndex]
            Log.v("OK", selectedEmployee!!.toString())
            firstName = selectedEmployee!!.firstName
            surname = selectedEmployee!!.surname
        }
    }

    fun updateEmployee() {
        selectedEmployee!!.firstName = firstName
        selectedEmployee!!.surname = surname
        repo.edit(selectedEmployee!!)
    }

    // Define ViewModel factory in a companion object
    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                EditEmployeeViewModel(repo = MyAppApplication.employeeInMemoryRepository
                )
            }
        }
    }
}
