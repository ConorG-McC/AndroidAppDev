package com.example.androidappdev.core

import android.app.Application
import android.util.Log
import com.example.androidappdev.data.Employee
import com.example.androidappdev.data.InMemoryRepository
import java.util.UUID
import kotlin.collections.ArrayList


class MyAppApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        employeeInMemoryRepository = InMemoryRepository(ArrayList()).apply {
            addEmployee(Employee(UUID.randomUUID(), "firstname1", "surname1"))
            addEmployee(Employee(UUID.randomUUID(), "firstname2", "surname2"))
            addEmployee(Employee(UUID.randomUUID(), "firstname3", "surname3"))
        }
        Log.v("MyAppApplication.onCreate TEST",
            employeeInMemoryRepository.getAllEmployee().toString()
        )
    }

    companion object {
        lateinit var employeeInMemoryRepository: InMemoryRepository
    }
}
