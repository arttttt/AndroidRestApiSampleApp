package com.arttttt.restapisample.model.user.base

import com.arttttt.restapisample.model.user.User

interface UsersRepository {
    fun getUsers(onCompletion: (List<User>) -> Unit, onError: (String) -> Unit)
    fun addUser(user: User)
    fun updateUser(user: User)
}