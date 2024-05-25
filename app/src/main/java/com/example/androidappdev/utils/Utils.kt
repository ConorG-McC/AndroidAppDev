package com.example.androidappdev.utils

import android.content.ContentValues.TAG
import android.content.Context
import android.util.Log
import android.widget.Toast.LENGTH_LONG
import android.widget.Toast.makeText
import androidx.compose.ui.graphics.Color
import com.example.androidappdev.data.task.TaskStatus

class Utils {
    companion object {
        fun print(e: Exception) = Log.e(TAG, e.stackTraceToString())
        fun showMessage(context: Context, message: String?
        ) = makeText(context, message, LENGTH_LONG).show()

        fun getStatusColor(status: TaskStatus): Color {
            return when (status) {
                TaskStatus.TODO -> Color(0xFFA9A9A9) // Dark Gray
                TaskStatus.IN_PROGRESS -> Color(0xFFFFA500) // Orange
                TaskStatus.DONE -> Color(0xFF008000) // Green
            }
        }
    }
}