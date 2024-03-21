package com.example.androidappdev.presentation.screens.employees.view

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.androidappdev.core.MyAppApplication
import com.example.androidappdev.data.employee.Employee
import com.example.androidappdev.data.employee.EmployeeRepo
import com.example.androidappdev.data.task.Task
import com.example.navigationwithviewmodel1.data.DatabaseResult
import com.example.navigationwithviewmodel1.data.DatabaseState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class EmployeeViewModel(private val repo: EmployeeRepo) :
    ViewModel() {

    private val _userState = MutableStateFlow(DatabaseState<Employee>())
    val userState: StateFlow<DatabaseState<Employee>> = _userState.asStateFlow()//Monitored by component for recomposition on change

    val items = mutableStateListOf<Employee>()
    var selectedEmployee: Employee?= null

    fun employeeHasBeenSelected(): Boolean = selectedEmployee!=null

    init {
        getEmployees()
    }

    private fun getEmployees() = viewModelScope.launch {
        repo.getAllEmployees().collect { result ->
            when(result) {
                is DatabaseResult.Success -> {
                    _userState.update { it.copy(data = result.data) }
                }

                is DatabaseResult.Error -> {
                    _userState.update {
                        it.copy(errorMessage = result.exception.message!!)
                    }
                }

                is DatabaseResult.Loading -> {
                    _userState.update { it.copy(isLoading = true) }
                }
            }
        }
    }
    fun deleteEmployee() {
        if (employeeHasBeenSelected()) {
            repo.deleteEmployee(selectedEmployee!!)
            selectedEmployee = null
        }
    }

//     Define ViewModel factory in a companion object
    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                EmployeeViewModel(repo = MyAppApplication.container.employeeRepository
                )
            }
        }
    }
}
