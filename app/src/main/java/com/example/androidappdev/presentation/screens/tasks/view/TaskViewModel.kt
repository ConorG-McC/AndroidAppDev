package com.example.androidappdev.presentation.screens.tasks.view

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.androidappdev.core.MyAppApplication
import com.example.androidappdev.data.entities.Task
import com.example.androidappdev.data.repositories.TaskRepository

class TaskViewModel(private val repo: TaskRepository) : ViewModel() {
    val items = mutableStateListOf<Task>()//No live data in this example
    var selectedTaskIndex: Int = -1

    init {
        Log.v("OK", "init home TEST")
        items.addAll(repo.getAllTasks())
        Log.v("OK", "Number of tasks loaded TEST: ${items.size}")
    }

    fun deleteTask() {
        repo.deleteTask(repo.getAllTasks()[selectedTaskIndex])
        items.removeAt(selectedTaskIndex)
        selectedTaskIndex = -1
        Log.v("OK", "repo size " + repo.getAllTasks().size.toString())
    }

    // Define ViewModel factory in a companion object
    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                TaskViewModel(repo = MyAppApplication.taskRepository
                )
            }
        }

    }
}
