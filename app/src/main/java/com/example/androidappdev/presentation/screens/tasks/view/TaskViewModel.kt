package com.example.androidappdev.presentation.screens.tasks.view

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.androidappdev.core.MyAppApplication
import com.example.androidappdev.data.task.Task
import com.example.androidappdev.data.task.TaskRepo
import com.example.androidappdev.data.database.DatabaseResult
import com.example.androidappdev.data.database.DatabaseState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class TaskViewModel(private val repo: TaskRepo) : ViewModel() {
    private val _userState = MutableStateFlow(DatabaseState<Task>())
    val userState: StateFlow<DatabaseState<Task>> = _userState.asStateFlow()//Monitored by component for recomposition on change

    val items = mutableStateListOf<Task>()
    var selectedTask: Task?= null
    fun taskHasBeenSelected(): Boolean = selectedTask!=null

    init {
        getTasks()
    }

    private fun getTasks() = viewModelScope.launch {
        repo.getAllTasks().collect { result ->
            when(result) {
                is DatabaseResult.Success -> {
                    _userState.update { it.copy(data = result.data) }
                }
                is DatabaseResult.Error -> {
                    _userState.update {
                        it.copy(errorMessage = result.exception.message!!)
                    }
                }
                is DatabaseResult.Loading -> {
                    _userState.update { it.copy(isLoading = true) }
                }
            }
        }
    }

    fun deleteTask(){
        if (taskHasBeenSelected()) {
            repo.deleteTask(selectedTask!!)
            selectedTask = null
        }
    }

    // Define ViewModel factory in a companion object
    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                TaskViewModel(repo = MyAppApplication.container.taskRepository
                )
            }
        }

    }
}
