package com.example.androidappdev.presentation.screens.tasks.edit

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.androidappdev.core.MyAppApplication
import com.example.androidappdev.data.auth.AuthRepo
import com.example.androidappdev.data.task.Task
import com.example.androidappdev.data.task.TaskRepo
import com.example.androidappdev.data.task.TaskStatus

class EditTaskViewModel(private val authRepo: AuthRepo,
                        private val repo: TaskRepo
) : ViewModel() {
    private var selectedTask: Task? = null

    var id by mutableStateOf("")
    var title by mutableStateOf("")
    var description by mutableStateOf("")

    private var _status = MutableLiveData<TaskStatus>()
    val status: LiveData<TaskStatus> = _status
    fun setSelectedTask(task: Task) {
        id = task.id.toString()
        title = task.title.toString()
        description = task.description.toString()
        _status.value = task.status
        selectedTask = task
    }

    fun onStatusChange(status: TaskStatus) {
        _status.value = status
    }

    fun titleIsValid(): Boolean {
        return title.isNotBlank()
    }

    fun descriptionIsValid(): Boolean {
        return description.isNotBlank()
    }

    fun updateTask() {
        selectedTask?.let {
            it.title = title
            it.description = description
            it.status = _status.value ?: TaskStatus.TODO
            repo.editTask(it, authRepo.currentUser!!.uid)
        }
    }

    fun allDataIsValid(): Boolean {
        return title.isNotBlank() && description.isNotBlank()
    }

    // Define ViewModel factory in a companion object
    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                EditTaskViewModel(authRepo = MyAppApplication.container.authRepository,
                                  repo = MyAppApplication.container.taskRepository
                )
            }
        }
    }
}
