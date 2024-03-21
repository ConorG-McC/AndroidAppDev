package com.example.androidappdev.data.employee

data class Employee(
        var firstName: String? = null,
        var surname: String? = null
) {
    var id: String? = null
    override fun toString(): String =
        "Employee ID: $id \nEmployee Name: $firstName $surname"

}
