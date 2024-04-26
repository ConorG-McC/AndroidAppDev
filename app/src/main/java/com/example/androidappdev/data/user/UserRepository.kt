package com.example.androidappdev.data.user

import com.google.android.gms.tasks.Task

interface UserRepo {
    fun add(user: User, uid: String): Task<Void>
}

class UserRepository(private val dao: UserDAO) : UserRepo {
    override fun add(user: User, uuid: String) = dao.insert(user, uuid)
}