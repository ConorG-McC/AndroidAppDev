package com.example.androidappdev.presentation.screens.employees.view

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.androidappdev.core.MyAppApplication
import com.example.androidappdev.data.Employee
import com.example.androidappdev.data.InMemoryRepository

class EmployeeViewModel(private val repo: InMemoryRepository) : ViewModel() {
    val items = mutableStateListOf<Employee>()//No live data in this example
    var selectedEmployeeIndex: Int =-1
    init {
        Log.v("OK", "init home TEST")
        items.addAll(repo.getAllEmployee())
        Log.v("OK", "Number of employees loaded TEST: ${items.size}")
    }

    fun deleteEmployee(){
        repo.deleteEmployee(repo.getAllEmployee()[selectedEmployeeIndex])
        items.removeAt(selectedEmployeeIndex)
        selectedEmployeeIndex=-1
        Log.v("OK","repo size " + repo.getAllEmployee().size.toString())
    }

    // Define ViewModel factory in a companion object
    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                EmployeeViewModel(
                    repo = MyAppApplication.employeeInMemoryRepository
                )
            }
        }

    }
}
