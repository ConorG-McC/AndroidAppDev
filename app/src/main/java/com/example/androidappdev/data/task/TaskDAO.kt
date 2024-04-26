package com.example.androidappdev.data.task

import com.example.androidappdev.data.database.DatabaseResult
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import java.util.UUID

class TaskDAO(private val database: DatabaseReference) {
    suspend fun getAllTasks(currentUserId: String): Flow<DatabaseResult<List<Task?>>> =
        callbackFlow {
            trySend(DatabaseResult.Loading)
            database.child(currentUserId).keepSynced(true)

            val event = object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val tasks = ArrayList<Task>()
                    for (childSnapshot in snapshot.children) {
                        val task = childSnapshot.getValue(Task::class.java)
                        task!!.id = childSnapshot.key.toString()
                        tasks.add(task)
                    }
                    trySend(DatabaseResult.Success(tasks))
                }

                override fun onCancelled(error: DatabaseError) {
                    trySend(DatabaseResult.Error(Throwable(error.message)))
                }
            }
            database.child(currentUserId).addValueEventListener(event)
            awaitClose { close() }
        }

    fun insert(newTask: Task, currentUserId: String) =
        database.child(currentUserId).child(UUID.randomUUID().toString())
            .setValue(newTask)

    fun update(editTask: Task, currentUserId: String) =
        database.child(currentUserId).child(editTask.id.toString())
            .setValue(editTask)

    fun delete(task: Task, currentUserId: String) =
        database.child(currentUserId).child(task.id.toString()).removeValue()

}
