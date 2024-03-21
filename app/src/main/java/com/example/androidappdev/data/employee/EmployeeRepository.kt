package com.example.androidappdev.data.employee

import android.util.Log
import com.example.androidappdev.data.employee.Employee
import com.example.navigationwithviewmodel1.data.DatabaseResult
import com.google.android.gms.tasks.Task
import kotlinx.coroutines.flow.Flow

interface EmployeeRepo {
    fun deleteEmployee(employee: Employee): Task<Void>
    fun addEmployee(employee: Employee): Task<Void>
    fun editEmployee(employee: Employee): Task<Void>
    suspend fun getAllEmployees(): Flow<DatabaseResult<List<Employee?>>>
}
class EmployeeRepository(private val employeeDAO: EmployeeDAO): EmployeeRepo {

    override fun deleteEmployee(employee: Employee): Task<Void> {
        return employeeDAO.delete(employee)
    }

    override fun addEmployee(employee: Employee): Task<Void> {
        return employeeDAO.insert(employee)
    }

    override fun editEmployee(employee: Employee): Task<Void> {
        return employeeDAO.update(employee)
    }

    override suspend fun getAllEmployees(): Flow<DatabaseResult<List<Employee?>>> {
        return employeeDAO.getAllEmployees()
    }
}
