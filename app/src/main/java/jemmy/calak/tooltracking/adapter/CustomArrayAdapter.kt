/*
 * Copyright (c) 12 - 2020.
 * github: https://github.com/jemmycalak
 * email: jemmy.sapta14@gmail.com
 */

package jemmy.calak.tooltracking.adapter

import android.content.Context
import android.widget.ArrayAdapter
import android.widget.Filter

class CustomArrayAdapter<T>(context: Context, textViewResourceId: Int, var items: MutableList<T>?)
    : ArrayAdapter<T>(context, textViewResourceId, items as MutableList<T>) {
    private val filter = KNoFilter()

    override fun getFilter(): Filter {
        return filter
    }

    private inner class KNoFilter : Filter() {
        override fun performFiltering(arg0: CharSequence?): FilterResults {
            val result = FilterResults()

            result.values = items
            result.count = items?.size ?: 0

            return result
        }
        override fun publishResults(charSequence: CharSequence?, filterResults: FilterResults?) {
            notifyDataSetChanged()
        }
    }
}
