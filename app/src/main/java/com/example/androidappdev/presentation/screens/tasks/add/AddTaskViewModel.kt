package com.example.androidappdev.presentation.screens.tasks.add

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.androidappdev.core.MyAppApplication
import com.example.androidappdev.data.task.Task
import com.example.androidappdev.data.task.TaskRepo
import com.example.androidappdev.data.task.TaskStatus

class AddTaskViewModel(private val repo: TaskRepo) : ViewModel() {
    private var _title = MutableLiveData(String())
    val title: LiveData<String> = _title
    fun onTitleChange(title: String) {
        _title.value = title
    }

    private var _description = MutableLiveData(String())
    val description: LiveData<String> = _description
    fun onDescriptionChange(description: String) {
        _description.value = description
    }

    private var _status = MutableLiveData(TaskStatus.TODO)
    val status: LiveData<TaskStatus> = _status
    fun onStatusChange(status: TaskStatus) {
        _status.value = status
    }

    private fun allDataIsValid(): Boolean {
        return _title.value!!.isNotBlank() && _description.value!!.isNotBlank()
    }

    fun add() {
        if (allDataIsValid()) {
            val newTask = Task(_title.value.toString(),
                               _description.value.toString(),
                                 _status.value!!
            )
            Log.d("NEW TASK TO ADD", newTask.toString())
            repo.addTask(newTask)
            clear()
        }
    }

    private fun clear() {
        _title.value = String()
        _description.value = String()
    }


    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                AddTaskViewModel(repo = MyAppApplication.container.taskRepository
                )
            }
        }

    }
}