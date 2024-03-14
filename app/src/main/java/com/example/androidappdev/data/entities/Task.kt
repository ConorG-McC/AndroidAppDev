package com.example.androidappdev.data.entities

import com.example.androidappdev.data.enums.TaskStatus
import java.util.UUID

data class Task(
        var title: String = String(),
        var description: String = String(),
        var status: TaskStatus = TaskStatus.TODO
) {
    var id: String? = null

    override fun toString(): String =
        " Task ID: $id \n Task Title: $title \n Task Description: $description \n Task Status: $status"
}
