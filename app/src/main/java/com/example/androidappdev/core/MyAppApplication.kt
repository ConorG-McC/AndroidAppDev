package com.example.androidappdev.core

import android.app.Application
import com.example.androidappdev.data.entities.Employee
import com.example.androidappdev.data.entities.Task
import com.example.androidappdev.data.repositories.EmployeeInMemoryRepository
import com.example.androidappdev.data.repositories.TaskInMemoryRepository
import java.util.UUID

class MyAppApplication : Application() {

    companion object {
        lateinit var employeeInMemoryRepository: EmployeeInMemoryRepository
        lateinit var taskInMemoryRepository: TaskInMemoryRepository
    }

    override fun onCreate() {
        super.onCreate()
        initEmployeeRepository()
        initTaskRepository()
    }

    private fun initEmployeeRepository() {
        employeeInMemoryRepository =
            EmployeeInMemoryRepository(ArrayList()).apply {
                addEmployee(Employee(UUID.randomUUID(), "firstname1", "surname1"
                )
                )
                addEmployee(Employee(UUID.randomUUID(), "firstname2", "surname2"
                )
                )
                addEmployee(Employee(UUID.randomUUID(), "firstname3", "surname3"
                )
                )
            }
    }

    private fun initTaskRepository() {
        taskInMemoryRepository = TaskInMemoryRepository(ArrayList()).apply {
            addTask(Task("Task1", "Description1"))
            addTask(Task("Task2", "Description2"))
            addTask(Task( "Task3", "Description3"))
        }

    }
}
