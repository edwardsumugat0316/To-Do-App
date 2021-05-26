package com.android.todo.modules

import com.android.todo.complete.CompleteActivityViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val completeActivityViewModelModule = module{
    viewModel { CompleteActivityViewModel(repository = get()) }
}