package com.arttttt.restapisample.model.user

import com.arttttt.restapisample.model.base.Result
import com.arttttt.restapisample.model.user.base.UsersRepository
import com.arttttt.restapisample.model.user.remote.UserApi
import kotlinx.coroutines.*

class UsersRepositoryImpl(private val userApi: UserApi): UsersRepository {

    private val scope: CoroutineScope = GlobalScope
    private val foregroundDispatcher: MainCoroutineDispatcher = Dispatchers.Main
    private val backgroundDispatcher: CoroutineDispatcher = Dispatchers.IO

    override fun getUsers(onCompletion: (List<User>) -> Unit, onError: (String) -> Unit) {
        scope.launch(foregroundDispatcher) {
            val result = withContext(backgroundDispatcher) {
                try {
                    val users = userApi.getUsers().await()
                    Result.Success(users)
                } catch (ex:Exception) {
                    Result.Error(ex)
                }
            }
            when (result) {
                is Result.Success<*> -> onCompletion(result.data as List<User>)
                is Result.Error -> result.ex.message?.let { onError(it) }
            }
        }
    }

    override fun addUser(user: User) {
        scope.launch(backgroundDispatcher) { userApi.addUser(user) }
    }

    override fun updateUser(user: User) {
        scope.launch(backgroundDispatcher) { userApi.updateUser(user) }
    }
}