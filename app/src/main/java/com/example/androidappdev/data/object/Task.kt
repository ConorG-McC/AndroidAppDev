package com.example.androidappdev.data.`object`

import java.util.UUID

data class Task(
    var id: UUID,
    var title: String = String(),
    var description: String = String(),
) {
    override fun toString(): String = " Task ID: $id \n Task Title: $title \n Task Description: $description"

}