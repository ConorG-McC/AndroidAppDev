package com.example.androidappdev.data.interfaces

import com.google.firebase.database.DatabaseReference

interface UpdateTaskListener {
    fun updateUserListener(taskToListenTo: DatabaseReference)
}