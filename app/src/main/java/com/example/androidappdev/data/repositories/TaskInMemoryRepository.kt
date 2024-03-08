package com.example.androidappdev.data.repositories

import android.util.Log
import com.example.androidappdev.data.`object`.Task

class TaskInMemoryRepository(private val taskRepository: MutableList<Task>) {

    fun getAllTasks(): MutableList<Task> {
        Log.v("InMemoryRepository.getAllEmployee TEST",
              taskRepository.toString()
        )
        return taskRepository
    }

    fun addTask(newTask: Task) {
        taskRepository.add(newTask)
    }

    fun deleteTask(taskToDelete: Task) {
        taskRepository.remove(taskToDelete)
    }

    fun edit(selectedTaskToEdit: Task) {
        //Find the task that we want to edit and then amend its details
        for (task in taskRepository.iterator()) {
            if (task.id == selectedTaskToEdit.id) {
                task.title = selectedTaskToEdit.title
                task.description = selectedTaskToEdit.description
            }
        }
    }
}
