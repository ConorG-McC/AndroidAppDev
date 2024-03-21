package com.example.androidappdev.data.task

data class Task(
        var title: String? = null,
        var description: String? = null,
        var status: TaskStatus = TaskStatus.TODO
) {
    var id: String? = null

    override fun toString(): String =
        " Task ID: $id \n Task Title: $title \n Task Description: $description \n Task Status: $status"

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || javaClass != other.javaClass) return false
        val task = other as Task
        return (task.id == id)
    }
}
