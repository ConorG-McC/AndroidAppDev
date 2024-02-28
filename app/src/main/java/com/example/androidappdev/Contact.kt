package com.example.androidappdev

data class Contact(
    var firstName: String = String(),
    var surname: String = String(),
    var telNo: String = String()
) {
    override fun toString(): String = "$firstName $surname ($telNo)"
}