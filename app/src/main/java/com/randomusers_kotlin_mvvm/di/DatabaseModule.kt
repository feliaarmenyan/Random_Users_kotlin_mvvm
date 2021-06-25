package com.randomusers_kotlin_mvvm.di

import android.app.Application
import androidx.room.Room
import com.randomusers_kotlin_mvvm.data.local.db.AppDatabase
import com.randomusers_kotlin_mvvm.data.local.db.dao.UserDao
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val databaseModule = module {

    fun provideDatabase(application: Application): AppDatabase {
        return Room.databaseBuilder(application, AppDatabase::class.java, "randomusersdb")
            .fallbackToDestructiveMigration()
            .build()
    }

    fun provideUserItemDao(database: AppDatabase): UserDao {
        return database.userDao()
    }

    single { provideDatabase(androidApplication()) }
    single { provideUserItemDao(get()) }


}