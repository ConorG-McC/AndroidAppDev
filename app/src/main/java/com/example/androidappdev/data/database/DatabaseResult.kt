package com.example.androidappdev.data.database


sealed class DatabaseResult<out Result> {
    data class Success<out T>(val data: T) : DatabaseResult<T>()
    data class Error(val exception: Throwable) : DatabaseResult<Nothing>()
    data object Loading : DatabaseResult<Nothing>()
}