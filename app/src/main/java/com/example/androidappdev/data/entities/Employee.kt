package com.example.androidappdev.data.entities

import java.util.UUID

data class Employee(var id: UUID,
                    var firstName: String,
                    var surname: String,
                    var teamId: UUID? = null,
                    var tasks: List<MutableList<Task>> = emptyList()
) {
    override fun toString(): String =
        "Employee ID: $id \n Employee Name: $firstName $surname \n Team ID: $teamId \n Tasks: $tasks"

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || javaClass != other.javaClass) return false
        val employee = other as Employee
        return (employee.id == id)
    }
}
