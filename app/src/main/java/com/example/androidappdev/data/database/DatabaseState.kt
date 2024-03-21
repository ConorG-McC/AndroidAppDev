package com.example.androidappdev.data.database

data class DatabaseState<T>(
    val data: List<T?> = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: String=""
)