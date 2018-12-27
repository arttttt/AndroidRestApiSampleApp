package com.arttttt.restapisample.utils

import android.util.Patterns

object ValidatorUtils {
    fun isFieldValid(field: String): Boolean = field.trim().isNotEmpty()
    fun isEmailFieldValid(field: String): Boolean = Patterns.EMAIL_ADDRESS.matcher(field).matches()
}