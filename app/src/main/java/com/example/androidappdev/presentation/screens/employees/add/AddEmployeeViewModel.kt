package com.example.androidappdev.presentation.screens.employees.add

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.androidappdev.core.MyAppApplication
import com.example.androidappdev.data.entities.Employee
import com.example.androidappdev.data.repositories.EmployeeRepository
import java.util.UUID

class AddEmployeeViewModel(private val repo: EmployeeRepository) : ViewModel() {

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

    private var _teamID = MutableLiveData<UUID?>()
    val teamID: MutableLiveData<UUID?> = _teamID
    private fun allDataIsValid(): Boolean {
        return _firstName.value!!.isNotBlank() && _surname.value!!.isNotBlank()
    }

    fun add() {
        if (allDataIsValid()) {
            val newEmployee = Employee(UUID.randomUUID(),
                                       _firstName.value.toString(),
                                       _surname.value.toString(),
                                       teamId = null,
                                       tasks = emptyList()
            )
            Log.d("NEW EMPLOYEE TO ADD", newEmployee.toString())
            repo.addEmployee(newEmployee)
            clear()
            Log.d("NEW EMPLOYEE ADDED TO REPO",
                  repo.getAllEmployee().size.toString()
            )

        }
    }

    private fun clear() {
        _firstName.value = String()
        _surname.value = String()
        _teamID.value = null
    }

    // Define ViewModel factory in a companion object
    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                AddEmployeeViewModel(repo = MyAppApplication.employeeRepository
                )
            }
        }

    }
}
