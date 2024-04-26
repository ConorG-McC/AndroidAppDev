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
    var taskStatus by mutableStateOf("")
    fun setSelectedTask(task: Task) {
        id = task.id.toString()
        title = task.title.toString()
        description = task.description.toString()
        taskStatus = task.status.toString()
        selectedTask = task
    }

    private var _status = MutableLiveData(TaskStatus.TODO)
    val status: LiveData<TaskStatus> = _status
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
        selectedTask!!.title = title
        selectedTask!!.description = description
        selectedTask!!.status = _status.value!!
        repo.editTask(selectedTask!!, authRepo.currentUser!!.uid)
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
