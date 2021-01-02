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
import jemmy.calak.tooltracking.databinding.LayoutItemToolsBinding
import jemmy.calak.tooltracking.model.Tools
import jemmy.calak.tooltracking.ui.tools.ListToolsViewModel

class ToolsAdapter(val listener: ListToolsViewModel) : ListAdapter<Tools, RecyclerView.ViewHolder>(ToolsDiffUtils()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ToolsViewHolder(LayoutItemToolsBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val data = getItem(position)
        (holder as ToolsViewHolder).bind(data, listener)
    }

    class ToolsViewHolder(val binding: LayoutItemToolsBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Tools, mlistener: ListToolsViewModel) {
            binding.apply {
                model = data
                listener = mlistener
                executePendingBindings()
            }
        }
    }
}

class ToolsDiffUtils : DiffUtil.ItemCallback<Tools>() {
    override fun areItemsTheSame(oldItem: Tools, newItem: Tools) = (oldItem.nameTool == newItem.nameTool)

    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(oldItem: Tools, newItem: Tools) = (oldItem == newItem)
}
