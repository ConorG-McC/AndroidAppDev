package com.example.androidappdev.core

import android.app.Application
import com.example.androidappdev.data.employee.Employee
import com.example.androidappdev.data.task.Task
import com.example.androidappdev.data.employee.EmployeeRepository
import com.example.androidappdev.data.task.TaskRepo
import java.util.UUID

class MyAppApplication : Application() {

    companion object {
        lateinit var container: AppDataContainer
    }

    override fun onCreate() {
        super.onCreate()
        container = AppDataContainer(this)
    }
}