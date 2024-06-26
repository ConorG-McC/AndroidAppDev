package com.example.androidappdev.presentation.screens.tasks.view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.androidappdev.core.MyAppApplication
import com.example.androidappdev.data.auth.AuthRepo
import com.example.androidappdev.data.database.DatabaseResult
import com.example.androidappdev.data.database.DatabaseState
import com.example.androidappdev.data.task.Task
import com.example.androidappdev.data.task.TaskRepo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class TaskViewModel(private val authRepo: AuthRepo, private val repo: TaskRepo
) : ViewModel() {
    private val _userState = MutableStateFlow(DatabaseState<Task>())
    val userState: StateFlow<DatabaseState<Task>> =
        _userState.asStateFlow()//Monitored by component for recomposition on change

    var selectedTask: Task? = null
    fun taskHasBeenSelected(): Boolean = selectedTask != null

    init {
        getListOfTasks(authRepo.currentUser!!.uid)
    }

    private fun getListOfTasks(currentUserID: String) = viewModelScope.launch {
        repo.getAllTasks(currentUserID).collect { result ->
            when (result) {
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

    fun deleteTask() {
        if (taskHasBeenSelected()) {
            repo.deleteTask(selectedTask!!, authRepo.currentUser!!.uid)
            selectedTask = null
        }
    }

    // Define ViewModel factory in a companion object
    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                TaskViewModel(authRepo = MyAppApplication.container.authRepository,
                              repo = MyAppApplication.container.taskRepository
                )
            }
        }

    }
}
