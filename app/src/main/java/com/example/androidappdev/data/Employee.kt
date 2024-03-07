package com.example.androidappdev.data

import java.util.UUID

data class Employee(
    var id: UUID,
    var firstName: String,
    var surname: String,
)  {
    override fun toString(): String = "Employee Name: $firstName $surname"

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || javaClass != other.javaClass) return false
        val employee = other as Employee
        return (employee.id == id)
    }
}