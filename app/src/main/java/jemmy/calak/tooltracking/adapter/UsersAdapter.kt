/*
 * Copyright (c) 12 - 2020.
 * github: https://github.com/jemmycalak
 * email: jemmy.sapta14@gmail.com
 */

package jemmy.calak.tooltracking.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import jemmy.calak.tooltracking.databinding.LayoutItemUsersBinding
import jemmy.calak.tooltracking.model.User
import jemmy.calak.tooltracking.ui.friends.FriendsViewModel

class UsersAdapter(val friendViewModel: FriendsViewModel) : ListAdapter<User, RecyclerView.ViewHolder>(UsersDiffUtils()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return UsersViewHolder(LayoutItemUsersBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val data = getItem(position)
        (holder as UsersViewHolder).bind(data, friendViewModel)
    }

    class UsersViewHolder(val binding: LayoutItemUsersBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(data: User, friendViewModel: FriendsViewModel) {
            binding.apply {
                model = data
                viewModel = friendViewModel
                executePendingBindings()
            }
        }
    }
}

class UsersDiffUtils : DiffUtil.ItemCallback<User>() {
    override fun areItemsTheSame(oldItem: User, newItem: User) = (oldItem.name == newItem.name)

    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(oldItem: User, newItem: User) = (oldItem == newItem)
}
