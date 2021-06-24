package com.randomusers_kotlin_mvvm.data.remote.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class RandomUsers(
    val results: List<User>
) : Parcelable