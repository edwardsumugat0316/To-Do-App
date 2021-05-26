package com.android.todo.modules

import com.android.todo.todoInfoActivity.TodoActivityViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val todoActivityViewModel = module {
    viewModel { TodoActivityViewModel (repository = get()) }
}