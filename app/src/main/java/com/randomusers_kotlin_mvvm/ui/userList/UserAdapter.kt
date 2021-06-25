package com.randomusers_kotlin_mvvm.ui.userList

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.randomusers_kotlin_mvvm.core.utils.displayWidth
import com.randomusers_kotlin_mvvm.core.utils.dpToPx
import com.randomusers_kotlin_mvvm.core.utils.loadImage
import com.randomusers_kotlin_mvvm.data.remote.model.User
import com.randomusers_kotlin_mvvm.data.remote.model.User.Companion.COMPARATOR
import com.randomusers_kotlin_mvvm.databinding.ItemUserBinding

class UserAdapter :
    PagingDataAdapter<User, UserAdapter.UserViewHolder>(COMPARATOR) {

    var onUserItemClick: ((User) -> Unit)? = null
    var onUserPhoneItemClick: ((User) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemUserBinding.inflate(layoutInflater, parent, false)
        val width = ((binding.userIcon.context.displayWidth() - 24.dpToPx) / 3)
        binding.userIcon.layoutParams.width = width
        binding.userIcon.layoutParams.height = width
        return UserViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bindTo(getItem(position))
    }

    inner class UserViewHolder(var binding: ItemUserBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bindTo(user: User?) {
            with(binding) {
                userTitle.text = user?.name?.first + " " + user?.name?.last

                userIcon.loadImage(user?.picture?.large)
                userItem.setOnClickListener {
                    user?.let {
                        onUserItemClick?.invoke(it)
                    }
                }
                userPhone.setOnClickListener {
                    user?.let {
                        onUserPhoneItemClick?.invoke(it)
                    }
                }
            }
        }
    }
}