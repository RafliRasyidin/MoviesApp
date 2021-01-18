package com.rasyidin.moviesapp.core.ui.base

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

abstract class BaseAdapter<T>(
    private val layoutId: Int
) : RecyclerView.Adapter<BaseAdapter.BaseViewHolder>() {

    class BaseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    abstract val data: ArrayList<T>

    fun setList(listItems: List<T>) {
        if (listItems.isNullOrEmpty()) return
        data.clear()
        data.addAll(listItems)
        notifyDataSetChanged()
    }

    protected var onItemClickListener: ((T) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return BaseViewHolder(
            LayoutInflater.from(parent.context).inflate(layoutId, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return data.size
    }

    fun setItemClickListener(listener: (T) -> Unit) {
        onItemClickListener = listener
    }
}