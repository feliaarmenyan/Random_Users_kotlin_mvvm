package com.randomusers_kotlin_mvvm.di

import com.randomusers_kotlin_mvvm.data.remote.repository.RandomUsersRepository
import com.randomusers_kotlin_mvvm.data.remote.repository.RandomUsersRepositoryImpl
import org.koin.dsl.module

val appModule = module {

    single<RandomUsersRepository> {
        RandomUsersRepositoryImpl(
            randomUsersApi = get()
        )
    }
}