package com.example.androidappdev.data.interfaces

import com.example.androidappdev.data.database.DatabaseResult
import com.google.android.gms.tasks.Task
import kotlinx.coroutines.flow.Flow

interface Repo<T> {
    fun delete(entry: T): Task<Void>
    fun add(entry: T): Task<Void>
    fun edit(entry: T): Task<Void>
    suspend fun getAll(): Flow<DatabaseResult<List<T?>>>
}