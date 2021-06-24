package com.randomusers_kotlin_mvvm.di

import com.randomusers_kotlin_mvvm.ui.RandomUsersViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelsModule = module {

    viewModel { RandomUsersViewModel(repository = get()) }
}