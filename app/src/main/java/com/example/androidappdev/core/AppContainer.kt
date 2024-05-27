package com.example.androidappdev.core

import android.util.Log
import com.example.androidappdev.data.auth.AuthRepo
import com.example.androidappdev.data.auth.AuthRepository
import com.example.androidappdev.data.employee.EmployeeDAO
import com.example.androidappdev.data.employee.EmployeeRepository
import com.example.androidappdev.data.task.TaskDAO
import com.example.androidappdev.data.task.TaskRepo
import com.example.androidappdev.data.task.TaskRepository
import com.example.androidappdev.data.user.UserDAO
import com.example.androidappdev.data.user.UserRepo
import com.example.androidappdev.data.user.UserRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

private const val DATABASE_URL =
    "https://androidapplicationmodule-default-rtdb.europe-west1.firebasedatabase.app/"
private const val TASK_FOLDER = "tasks"
private const val EMPLOYEE_FOLDER = "employees"
private const val USERS_ROOT_FOLDER = "users"

interface AppContainer {
    val taskRepository: TaskRepo
    val employeeRepository: EmployeeRepository
    val userRepository: UserRepo
    val authRepository: AuthRepo

    val isRunningTest: Boolean
}

class AppDataContainer() : AppContainer {

    override val isRunningTest: Boolean by lazy {
        try {
            Class.forName("androidx.test.espresso.Espresso")
            Log.d("AppDataContainer", "Running test")
            true
        } catch (e: ClassNotFoundException) {
            Log.d("AppDataContainer", "Not running test")
            false
        }
    }

    override val taskRepository: TaskRepo
    override val employeeRepository: EmployeeRepository
    override val userRepository: UserRepo
    override var authRepository: AuthRepo =
        AuthRepository(FirebaseAuth.getInstance())

    init {
        val APPENDED_TEST_PATH = if (isRunningTest) "test" else String()

        // tasks
        val taskDatabase = FirebaseDatabase.getInstance(DATABASE_URL)
            .getReference("$APPENDED_TEST_PATH$TASK_FOLDER")
        val taskDAO = TaskDAO(taskDatabase)
        taskRepository = TaskRepository(taskDAO)

        // employees
        val employeeDatabase = FirebaseDatabase.getInstance(DATABASE_URL)
            .getReference("$APPENDED_TEST_PATH$EMPLOYEE_FOLDER")
        val employeeDAO = EmployeeDAO(employeeDatabase)
        employeeRepository = EmployeeRepository(employeeDAO)

        val userRoot = FirebaseDatabase.getInstance(DATABASE_URL)
            .getReference("$APPENDED_TEST_PATH$USERS_ROOT_FOLDER")
        Log.d("AppDataContainer", "userRoot: $userRoot")
        val userDAO = UserDAO(userRoot)
        userRepository = UserRepository(userDAO)

    }
}