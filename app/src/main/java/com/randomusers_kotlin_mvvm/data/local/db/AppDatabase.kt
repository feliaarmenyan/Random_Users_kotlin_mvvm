package com.randomusers_kotlin_mvvm.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.randomusers_kotlin_mvvm.data.local.db.dao.UserDao
import com.randomusers_kotlin_mvvm.data.local.db.entity.UserEntity

@Database(entities = [UserEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}
