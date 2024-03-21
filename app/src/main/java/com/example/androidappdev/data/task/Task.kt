package com.example.androidappdev.data.task

data class Task(
        var title: String? = null,
        var description: String? = null,
        var status: TaskStatus = TaskStatus.TODO
) {
    var id: String? = null

    override fun toString(): String =
        " Task ID: $id \n Task Title: $title \n Task Description: $description \n Task Status: $status"

}
