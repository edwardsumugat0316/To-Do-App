package com.android.todo.modules

import com.android.todo.mainActivity.MainActivityViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val mainActivityViewModelModule = module {
    viewModel { MainActivityViewModel(repository = get())}
}