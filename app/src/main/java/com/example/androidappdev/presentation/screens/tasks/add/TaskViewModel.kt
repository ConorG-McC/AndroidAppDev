package com.example.androidappdev.presentation.screens.tasks.add

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.androidappdev.data.Task
import java.util.UUID

class TaskViewModel : ViewModel() {
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

    fun allDataIsValid(): Boolean {
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
            clear()

            Log.d("NEW TASK", newTask.toString())

        }
    }

    private fun clear() {
        _title.value = String()
        _description.value = String()
    }
}