package com.example.androidappdev.data.task

import com.example.navigationwithviewmodel1.data.DatabaseResult
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import java.util.*

class TaskDAO(private val database: DatabaseReference) {
     suspend fun getAllTasks() : Flow<DatabaseResult<List<Task?>>> = callbackFlow {
        trySend(DatabaseResult.Loading)
        database.keepSynced(true)

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
        database.addValueEventListener(event)
        awaitClose { close() }
    }

    fun insert(newTask: Task) = database.child(UUID.randomUUID().toString()).setValue(newTask)

    fun update(editTask: Task) = database.child(editTask.id.toString()).setValue(editTask)

    fun delete(task: Task) = database.child(task.id.toString()).removeValue()
}
