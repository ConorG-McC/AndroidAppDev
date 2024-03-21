package com.example.androidappdev.data.employee

import java.util.UUID

data class Employee(
        var firstName: String? = null,
        var surname: String? = null
) {
    var id: String? = null
    override fun toString(): String =
        "Employee ID: $id \nEmployee Name: $firstName $surname"

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || javaClass != other.javaClass) return false
        val employee = other as Employee
        return (employee.id == id)
    }
}
