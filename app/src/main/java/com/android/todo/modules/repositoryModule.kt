package com.android.todo.modules

import com.android.todo.repository.Repository
import com.android.todo.repository.RepositoryImp
import org.koin.dsl.module

val repositoryModule = module {
    single <Repository> {RepositoryImp(dataBase = get())  }
}