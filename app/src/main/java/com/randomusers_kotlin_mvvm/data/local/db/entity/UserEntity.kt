package com.randomusers_kotlin_mvvm.data.local.db.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "users")
@Parcelize
data class UserEntity(
    @PrimaryKey val email: String,
    @ColumnInfo(name = "name") val name: String?,
    @ColumnInfo(name = "picture") val picture: String?,
    @ColumnInfo(name = "phone") val phone: String?
) : Parcelable