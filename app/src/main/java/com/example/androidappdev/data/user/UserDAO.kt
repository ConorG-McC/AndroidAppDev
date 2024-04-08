package com.example.androidappdev.data.user

import com.google.firebase.database.DatabaseReference

class UserDAO(private val database: DatabaseReference) {
    fun insert(newUser: User, uuid: String) =
        database.child(uuid).setValue(newUser)
}
