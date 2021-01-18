package com.rasyidin.moviesapp.core.ui.base

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

abstract class BasePagedListAdapter<T>(
    val onClick: ((T) -> Unit)? = {},
    diffUtil: DiffUtil.ItemCallback<T>
) : PagedListAdapter<T, BasePagedListAdapter<T>.ViewHolder>(diffUtil) {

    protected abstract val getLayoutIdRes: Int

    private var items: MutableList<T> = mutableListOf()

    protected fun onItemClick(item: T?) = onClick?.let {
        if (item != null) {
            it(item)
        }
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(getLayoutIdRes, parent, false)
        )
    }

    fun getSwipeData(swipedPosition: Int): T? = getItem(swipedPosition)
}