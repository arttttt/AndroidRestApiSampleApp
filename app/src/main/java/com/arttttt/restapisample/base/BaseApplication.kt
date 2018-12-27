package com.arttttt.restapisample.base

import android.app.Application
import com.arttttt.restapisample.di.AppModule
import org.koin.android.ext.android.startKoin

class BaseApplication: Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin(this, listOf(AppModule.module))
    }
}