package com.android.todo.modules

import android.app.Application
import com.android.todo.database.AppDataBase
import org.koin.dsl.module

val dataBaseModule = module {
    single <AppDataBase> {AppDataBase.getDataBase(get()) }
}