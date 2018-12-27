package com.arttttt.restapisample.model.base

sealed class Result {
    data class Success<T>(val data: T): Result()
    data class Error(val ex: Throwable): Result()
}