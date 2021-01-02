/*
 * Copyright (c) 12 - 2020.
 * github: https://github.com/jemmycalak
 * email: jemmy.sapta14@gmail.com
 */

package jemmy.calak.tooltracking.adapter

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.DialogInterface
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import jemmy.calak.tooltracking.R
import jemmy.calak.tooltracking.databinding.LayoutItemTransactionBinding
import jemmy.calak.tooltracking.model.TransactionModel
import jemmy.calak.tooltracking.ui.transaction.TransactionViewModel
import jemmy.calak.tooltracking.utils.STATUS_BORROWED

class TransactionAdapter(val TAG: String? = null, val viewModel: TransactionViewModel) :
    ListAdapter<TransactionModel, RecyclerView.ViewHolder>(TransactionDiffUtils()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return TransactionViewHolder(
            LayoutItemTransactionBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val data = getItem(position)
        (holder as TransactionViewHolder).bind(data, TAG, viewModel)
    }

    class TransactionViewHolder(val binding: LayoutItemTransactionBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: TransactionModel, TAG: String?, viewModel: TransactionViewModel) {
            binding.apply {
                model = data
                tag = TAG
                executePendingBindings()

                root.setOnClickListener {
                    if (data.borrowed.status.equals(STATUS_BORROWED))
                        AlertDialog.Builder(it.context).apply {
                            setTitle(it.context.getString(R.string.alert))
                            setMessage(it.context.getString(R.string.ask_return_tool))
                            setNegativeButton(
                                it.context.getString(R.string.no),
                                object : DialogInterface.OnClickListener {
                                    override fun onClick(dialog: DialogInterface?, which: Int) {
                                        dialog?.dismiss()
                                    }
                                })
                            setPositiveButton(
                                it.context.getString(R.string.yes),
                                object : DialogInterface.OnClickListener {
                                    override fun onClick(dialog: DialogInterface?, which: Int) {
                                        viewModel.returnTools(data)
                                    }
                                })
                            show()
                        }
                }
            }
        }
    }
}

class TransactionDiffUtils : DiffUtil.ItemCallback<TransactionModel>() {
    override fun areItemsTheSame(oldItem: TransactionModel, newItem: TransactionModel) =
        (oldItem.borrowed.id == newItem.borrowed.id)

    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(oldItem: TransactionModel, newItem: TransactionModel) =
        (oldItem == newItem)
}
