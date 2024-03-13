package com.example.androidappdev.core

import android.app.Application
import com.example.androidappdev.data.entities.Employee
import com.example.androidappdev.data.entities.Task
import com.example.androidappdev.data.repositories.EmployeeRepository
import com.example.androidappdev.data.repositories.TaskRepository
import java.util.UUID

class MyAppApplication : Application() {

    companion object {
        lateinit var employeeRepository: EmployeeRepository
        lateinit var taskRepository: TaskRepository
    }

    override fun onCreate() {
        super.onCreate()
        initTaskRepository()
        initEmployeeRepository()

    }

    private fun initTaskRepository() {
        taskRepository = TaskRepository(ArrayList()).apply {
            addTask(Task(UUID.randomUUID(), "Task1", "Description1"))
            addTask(Task(UUID.randomUUID(), "Task2", "Description2"))
            addTask(Task(UUID.randomUUID(), "Task3", "Description3"))
        }

    }

    private fun initEmployeeRepository() {
        employeeRepository = EmployeeRepository(ArrayList()).apply {
            addEmployee(Employee(UUID.randomUUID(),
                                 "firstname1",
                                 "surname1",
                                 UUID.randomUUID(),
                                 listOf(taskRepository.getAllTasks())
            )
            )
            addEmployee(Employee(UUID.randomUUID(),
                                 "firstname2",
                                 "surname2",
                                 UUID.randomUUID(),
                                 listOf(taskRepository.getAllTasks()
                                 )
            )
            )
            addEmployee(Employee(UUID.randomUUID(),
                                 "firstname3",
                                 "surname3",
                                 UUID.randomUUID(),
                                 listOf(taskRepository.getAllTasks())
            )
            )
        }
    }
}
