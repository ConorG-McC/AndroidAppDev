package com.example.androidappdev.data.employee

import com.example.androidappdev.data.database.DatabaseResult
import com.google.android.gms.tasks.Task
import kotlinx.coroutines.flow.Flow

interface EmployeeRepo {
    fun deleteEmployee(employee: Employee, currentUserId: String): Task<Void>
    fun addEmployee(employee: Employee, currentUserId: String): Task<Void>
    fun editEmployee(employee: Employee, currentUserId: String): Task<Void>
    suspend fun getAllEmployees(currentUserId: String): Flow<DatabaseResult<List<Employee?>>>
}

class EmployeeRepository(private val employeeDAO: EmployeeDAO) : EmployeeRepo {

    override fun deleteEmployee(employee: Employee,
                                currentUserId: String
    ): Task<Void> {
        return employeeDAO.delete(employee, currentUserId)
    }

    override fun addEmployee(employee: Employee,
                             currentUserId: String
    ): Task<Void> {
        return employeeDAO.insert(employee, currentUserId)
    }

    override fun editEmployee(employee: Employee,
                              currentUserId: String
    ): Task<Void> {
        return employeeDAO.update(employee, currentUserId)
    }

    override suspend fun getAllEmployees(currentUserId: String): Flow<DatabaseResult<List<Employee?>>> {
        return employeeDAO.getAllEmployees(currentUserId)
    }
}
