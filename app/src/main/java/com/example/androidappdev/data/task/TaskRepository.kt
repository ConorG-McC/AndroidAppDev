package com.example.androidappdev.data.task

import com.example.androidappdev.data.database.DatabaseResult
import com.google.android.gms.tasks.Task as DBTask
import kotlinx.coroutines.flow.Flow

interface TaskRepo {
    fun deleteTask(task: Task): DBTask<Void>
    fun addTask(task: Task): DBTask<Void>
    fun editTask(task: Task): DBTask<Void>
    suspend fun getAllTasks(): Flow<DatabaseResult<List<Task?>>>

}
class TaskRepository(private val taskDAO: TaskDAO): TaskRepo {

    override fun deleteTask(task: Task): DBTask<Void> { return taskDAO.delete(task) }

    override fun addTask(task: Task): DBTask<Void> { return taskDAO.insert(task) }

    override fun editTask(task: Task): DBTask<Void> { return taskDAO.update(task) }

    override suspend fun getAllTasks(): Flow<DatabaseResult<List<Task?>>> { return taskDAO.getAllTasks() }



}
