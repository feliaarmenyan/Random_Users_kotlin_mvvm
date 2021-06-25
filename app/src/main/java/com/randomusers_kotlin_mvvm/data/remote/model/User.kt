package com.randomusers_kotlin_mvvm.data.remote.model

import android.os.Parcelable
import androidx.recyclerview.widget.DiffUtil
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
data class User(
    val gender: String? = null,
    val name: Name? = null,
    val location: Location? = null,
    val email: String,
    val login: Login? = null,
    val dob: Dob? = null,
    val registered: Registered? = null,
    val phone: String? = null,
    val picture: Picture? = null
) : Parcelable {
    companion object {
        val COMPARATOR: DiffUtil.ItemCallback<User> =
            object : DiffUtil.ItemCallback<User>() {
                override fun areItemsTheSame(
                    oldItem: User,
                    newItem: User
                ): Boolean {
                    return oldItem.email == newItem.email
                }

                override fun areContentsTheSame(
                    oldItem: User,
                    newItem: User
                ): Boolean {
                    return oldItem == newItem;

                }
            }
    }
}

@Parcelize
data class Name(
    val title: String? = null,
    val first: String? = null,
    val last: String? = null
) : Parcelable

@Parcelize
data class Location(
    val city: String? = null,
    val state: String? = null
) : Parcelable

@Parcelize
data class Login(
    val uuid: String? = null,
    val username: String? = null,
    val password: String? = null
) : Parcelable

@Parcelize
data class Dob(
    val date: Date? = null,
    val age: Int? = null
) : Parcelable

@Parcelize
data class Registered(
    val date: Date? = null,
    val age: Int? = null
) : Parcelable

@Parcelize
data class Picture(
    val large: String? = null
) : Parcelable
