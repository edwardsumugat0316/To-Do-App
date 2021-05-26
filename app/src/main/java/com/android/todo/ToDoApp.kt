package com.android.todo

import android.app.Application
import com.android.todo.modules.*
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class ToDoApp: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@ToDoApp)
            modules(dataBaseModule)
            modules(mainActivityViewModelModule)
            modules(repositoryModule)
            modules(todoActivityViewModel)
            modules(completeActivityViewModelModule)
        }
    }
}