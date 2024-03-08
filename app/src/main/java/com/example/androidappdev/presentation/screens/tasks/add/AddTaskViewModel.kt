package com.example.androidappdev.presentation.screens.tasks.add

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.androidappdev.core.MyAppApplication
import com.example.androidappdev.data.`object`.Task
import com.example.androidappdev.data.repositories.TaskInMemoryRepository
import java.util.UUID

class AddTaskViewModel(private val repo: TaskInMemoryRepository) : ViewModel() {
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

    private fun allDataIsValid(): Boolean {
        return _title.value!!.isNotBlank()
                && _description.value!!.isNotBlank()
    }

    fun add() {
        if (allDataIsValid()) {
            val newTask = Task(
                UUID.randomUUID(),
                _title.value.toString(),
                _description.value.toString()
            )
            Log.d("NEW TASK TO ADD", newTask.toString())
            repo.addTask(newTask)
            clear()
            Log.d("NEW TASK ADDED TO REPO", repo.getAllTasks().size.toString())

        }
    }

    private fun clear() {
        _title.value = String()
        _description.value = String()
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                AddTaskViewModel(
                    repo = MyAppApplication.taskInMemoryRepository
                )
            }
        }

    }
}