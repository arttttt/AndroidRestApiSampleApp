package com.arttttt.restapisample.model.user.remote

import com.arttttt.restapisample.model.user.User
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.Deferred
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

interface UserApi {
    @GET("users.json")
    fun getUsers(): Deferred<List<User>>

    @POST("users.json")
    fun addUser(@Body user: User): Deferred<User>

    @PATCH("users/1.json")
    fun updateUser(@Body user: User): Deferred<User>

    companion object Factory {
        fun create(): UserApi {
            val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://bb-test-server.herokuapp.com")
                .build()

            return retrofit.create(UserApi::class.java)
        }
    }
}