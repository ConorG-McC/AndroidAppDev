package com.example.androidappdev.data.employee

import com.example.androidappdev.data.database.DatabaseResult
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import java.util.UUID

class EmployeeDAO(private val database: DatabaseReference) {

    suspend fun getAllEmployees(currentUserId: String): Flow<DatabaseResult<List<Employee?>>> =
        callbackFlow {
            trySend(DatabaseResult.Loading)
            database.keepSynced(true)

            val event = object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val employees = ArrayList<Employee>()
                    for (childSnapshot in snapshot.children) {
                        val employee =
                            childSnapshot.getValue(Employee::class.java)
                        employee!!.id = childSnapshot.key.toString()
                        employees.add(employee)
                    }
                    trySend(DatabaseResult.Success(employees))
                }

                override fun onCancelled(error: DatabaseError) {
                    trySend(DatabaseResult.Error(Throwable(error.message)))
                }
            }
            database.child(currentUserId).addValueEventListener(event)
            awaitClose { close() }
        }

    fun insert(employee: Employee, currentUserId: String) =
        database.child(currentUserId).child(UUID.randomUUID().toString())
            .setValue(employee)

    fun update(editEmployee: Employee, currentUserId: String) =
        database.child(currentUserId).child(editEmployee.id.toString())
            .setValue(editEmployee)

    fun delete(task: Employee, currentUserId: String) =
        database.child(currentUserId).child(task.id.toString()).removeValue()
}
