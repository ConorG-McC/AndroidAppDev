package com.example.androidappdev.core

import android.content.Context
import com.example.androidappdev.data.employee.EmployeeDAO
import com.example.androidappdev.data.employee.EmployeeRepository
import com.example.androidappdev.data.task.TaskDAO
import com.example.androidappdev.data.task.TaskRepo
import com.example.androidappdev.data.task.TaskRepository
import com.google.firebase.database.FirebaseDatabase

private const val TASK_FOLDER = "tasks"
private const val EMPLOYEE_FOLDER = "employees"

interface AppContainer {
    val taskRepository: TaskRepo
    val employeeRepository: EmployeeRepository
//    val authRepository: AuthRepo
}

class AppDataContainer(private val context: Context) : AppContainer {
    override val taskRepository: TaskRepo
    override val employeeRepository: EmployeeRepository
//    override lateinit var authRepository: AuthRepo

    init {
        val firebase = FirebaseDatabase.getInstance("https://androidapplicationmodule-default-rtdb.europe-west1.firebasedatabase.app/")

        // tasks
        val taskDatabase = firebase.getReference(TASK_FOLDER)
        val taskDAO = TaskDAO(taskDatabase)
        taskRepository = TaskRepository(taskDAO)

        // employees
        val employeeDatabase = firebase.getReference(EMPLOYEE_FOLDER)
        val employeeDAO = EmployeeDAO(employeeDatabase)
        employeeRepository = EmployeeRepository(employeeDAO)
//        authRepository = AuthRepository(FirebaseAuth.getInstance())
    }
}