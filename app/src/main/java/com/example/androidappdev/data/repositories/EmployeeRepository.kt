package com.example.androidappdev.data.repositories

import android.util.Log
import com.example.androidappdev.data.entities.Employee

class EmployeeRepository(private val employeeRepository: MutableList<Employee>) {

    fun getAllEmployee(): MutableList<Employee> {
        Log.v("InMemoryRepository.getAllEmployee TEST",
              employeeRepository.toString()
        )
        return employeeRepository
    }

    fun addEmployee(newEmployee: Employee) {
        employeeRepository.add(newEmployee)
    }

    fun deleteEmployee(employeeToDelete: Employee) {
        employeeRepository.remove(employeeToDelete)
    }

    fun edit(selectedEmployeeToEdit: Employee) {
        //Find the employee that we want to edit and then amend its details
        for (employee in employeeRepository.iterator()) {
            if (employee.id == selectedEmployeeToEdit.id) {
                employee.firstName = selectedEmployeeToEdit.firstName
                employee.surname = selectedEmployeeToEdit.surname
            }
        }
    }
}