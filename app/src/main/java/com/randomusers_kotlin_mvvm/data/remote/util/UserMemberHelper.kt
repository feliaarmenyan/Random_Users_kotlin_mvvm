package com.randomusers_kotlin_mvvm.data.remote.util

import com.randomusers_kotlin_mvvm.data.local.db.dao.UserDao
import com.randomusers_kotlin_mvvm.data.local.db.entity.UserEntity
import com.randomusers_kotlin_mvvm.data.remote.model.User
import timber.log.Timber

object UserMemberHelper {
    suspend fun insertUserList(
        dao: UserDao,
        users: List<User>
    ) {
        for (user in users) {
            dao.insertAll(
                UserEntity(
                    user.email,
                    user.name?.first + user.name?.last,
                    user.picture?.large,
                    user.phone
                )
            )
        }
    }
}