/*
 * Copyright (c) 12 - 2020.
 * github: https://github.com/jemmycalak
 * email: jemmy.sapta14@gmail.com
 */

package jemmy.calak.tooltracking.utils

import android.widget.*
import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingAdapter
import androidx.recyclerview.widget.RecyclerView
import jemmy.calak.tooltracking.adapter.CustomArrayAdapter
import jemmy.calak.tooltracking.adapter.ToolsAdapter
import jemmy.calak.tooltracking.adapter.TransactionAdapter
import jemmy.calak.tooltracking.adapter.UsersAdapter
import jemmy.calak.tooltracking.model.*

object Bindings {

    @JvmStatic
    @BindingAdapter("app:listTools")
    fun setListTools(r: RecyclerView, data: List<Tools>?) {
        r.apply {
            if (!data.isNullOrEmpty()) (adapter as ToolsAdapter).submitList(data)
        }
    }

    @JvmStatic
    @BindingAdapter("app:listFriends")
    fun setListFriends(r: RecyclerView, data: List<User>?) {
        r.apply {
            if (!data.isNullOrEmpty()) (adapter as UsersAdapter).submitList(data)
        }
    }

    @JvmStatic
    @BindingAdapter("app:imageUrl")
    fun setImageUrl(img: ImageView, url: Int?) {
        img.apply {
            if (url != null) setImageDrawable(context.getDrawable(url))
        }
    }

    @JvmStatic
    @BindingAdapter("app:setTotalAvailable")
    fun setTotalItemAvailable(t: TextView, data: List<Tools>?) {
        t.apply {
            if (!data.isNullOrEmpty())
                text = data.let {
                    var count = 0
                    it.forEach { tools ->
                        count += (tools.quantity ?: 0)
                    }

                    "Total Items: ${count}"
                }
        }
    }

    @JvmStatic
    @BindingAdapter("app:setTotalBorrowed")
    fun setTotalBorrowe(t: TextView, data: List<Tools>?) {
        t.apply {
            if (!data.isNullOrEmpty())
                text = data.let {
                    var count = 0
                    it.forEach { tools ->
                        count += (tools.quantityBorrowed ?: 0)
                    }

                    "Total Items Borrowed: ${count}"
                }
        }
    }

    @JvmStatic
    @BindingAdapter("app:listFriendAutoComplete", "app:valueFriends")
    fun setFriendAutoComplete(t: AutoCompleteTextView, data: List<User>?, valuesFriends: String?) {
        t.apply {
            data.let {
                if (!it.isNullOrEmpty()) {
                    val newData = arrayListOf<String>()
                    data?.forEach { user -> newData.add(user.name) }

                    val adapters = CustomArrayAdapter(
                        context,
                        android.R.layout.simple_spinner_dropdown_item,
                        newData
                    )
                    setAdapter(adapters)
                    threshold = 1
                }
            }
        }
    }

    @JvmStatic
    @InverseBindingAdapter(
        attribute = "app:valueFriends",
        event = "android:textAttrChanged"
    )
    fun setFrindsAutoComplete(t: AutoCompleteTextView) = t.text.toString()

    @JvmStatic
    @InverseBindingAdapter(attribute = "app:setQuantity", event = "android:textAttrChanged")
    fun setQuantity(e: EditText) = if (e.text.toString().length > 0)e.text.toString().toInt() else 0


    @JvmStatic
    @BindingAdapter("app:setQuantity")
    fun setQuantitys(e: EditText, quantity: Int?) {
        if (e.text.toString() != quantity.toString() && quantity != null) e.setText(quantity.toString())
    }

    @JvmStatic
    @BindingAdapter(value = ["enableLend1", "enableLend2", "enableLend3", "enableLend4"])
    fun setEnableButtonLend(
        b: Button,
        friends: String?,
        quantity: Int?,
        model: Tools?,
        listfriends: List<User>?
    ) {
        b.apply {
            isEnabled = false
            if (friends.isNullOrEmpty() ||
                quantity == null ||
                model == null || listfriends.isNullOrEmpty()
            ) {
                isEnabled = false
                return@apply
            }
            if (quantity == 0 ||
                quantity > model.quantity!! ||
                model.quantity == 0
            ) {
                isEnabled = false
                return@apply
            }
            listfriends.forEach {
                if (it.name == friends) {
                    isEnabled = true
                }
            }
        }
    }

    @JvmStatic
    @BindingAdapter("app:listBorrower")
    fun setBorrower(r:RecyclerView, data:List<UserBorrowed>?) {
        r.apply {
            if (!data.isNullOrEmpty()) {
                val newData = arrayListOf<User>()
                data.forEach { newData.add(it.user) }
                (adapter as UsersAdapter).submitList(newData)
            }
        }
    }

    @JvmStatic
    @BindingAdapter("app:setTransaction")
    fun setDataTransaction(r:RecyclerView, data: List<TransactionModel>?) {
        r.apply {
            if (!data.isNullOrEmpty()) {
                (adapter as TransactionAdapter).submitList(data)
            }
        }
    }

}
