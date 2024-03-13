package com.example.androidappdev.presentation.screens.tasks.edit

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.androidappdev.core.MyAppApplication
import com.example.androidappdev.data.entities.Task
import com.example.androidappdev.data.repositories.TaskRepository

class EditTaskViewModel(private val repo: TaskRepository) : ViewModel() {
    private var selectedTask: Task? = null

    var title by mutableStateOf("")
    var description by mutableStateOf("")
    fun titleIsValid(): Boolean {
        return title.isNotBlank()
    }

    fun descriptionIsValid(): Boolean {
        return description.isNotBlank()
    }

    fun getTasks(selectedIndex: Int) {//Display when screen loads
        if (selectedTask == null) {
            selectedTask = repo.getAllTasks()[selectedIndex]
            Log.v("OK", selectedTask!!.toString())
            title = selectedTask!!.title
            description = selectedTask!!.description
        }
    }

    fun updateTask() {
        selectedTask!!.title = title
        selectedTask!!.description = description
        repo.edit(selectedTask!!)
    }

    // Define ViewModel factory in a companion object
    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                EditTaskViewModel(repo = MyAppApplication.taskRepository
                )
            }
        }
    }
}
