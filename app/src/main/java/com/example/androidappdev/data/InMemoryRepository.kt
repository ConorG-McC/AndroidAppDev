package com.example.androidappdev.data

import android.util.Log

class InMemoryRepository (private val employeeRepository:
                          MutableList<Employee>) {

    fun getAllEmployee(): MutableList<Employee>{
        Log.v("InMemoryRepository.getAllEmployee TEST",
            employeeRepository.toString()
        )
        return employeeRepository
    }

    fun addEmployee(newEmployee: Employee){
        employeeRepository.add(newEmployee)
    }

    fun deleteEmployee(employeeToDelete: Employee){
        employeeRepository.remove(employeeToDelete)
    }

    fun edit(selectedEmployeeToEdit: Employee){
        //Find the employee that we want to edit and then amend its details
        for (employee in employeeRepository.iterator()){
            if (employee.id==selectedEmployeeToEdit.id){
                employee.firstName = selectedEmployeeToEdit.firstName
                employee.surname = selectedEmployeeToEdit.surname
            }
        }
    }
}