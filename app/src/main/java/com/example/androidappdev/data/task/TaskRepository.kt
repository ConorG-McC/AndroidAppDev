package com.example.androidappdev.data.task

import com.example.androidappdev.data.database.DatabaseResult
import kotlinx.coroutines.flow.Flow
import com.google.android.gms.tasks.Task as DBTask

interface TaskRepo {
    fun deleteTask(task: Task): DBTask<Void>
    fun addTask(task: Task, currentUserId: String): DBTask<Void>
    fun editTask(task: Task, currentUserId: String): DBTask<Void>
    suspend fun getAllTasks(currentUserId: String): Flow<DatabaseResult<List<Task?>>>

}

class TaskRepository(private val taskDAO: TaskDAO) : TaskRepo {

    override fun deleteTask(task: Task): DBTask<Void> {
        return taskDAO.delete(task)
    }

    override fun addTask(task: Task, currentUserId: String): DBTask<Void> {
        return taskDAO.insert(task, currentUserId)
    }

    override fun editTask(task: Task, currentUserId: String): DBTask<Void> {
        return taskDAO.update(task, currentUserId)
    }

    override suspend fun getAllTasks(currentUserId: String): Flow<DatabaseResult<List<Task?>>> {
        return taskDAO.getAllTasks(currentUserId)
    }


}
