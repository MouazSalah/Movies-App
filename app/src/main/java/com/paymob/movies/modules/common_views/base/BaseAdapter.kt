package com.paymob.movies.modules.common_views.base

import android.annotation.SuppressLint
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

abstract class BaseAdapter<VB : ViewBinding, T : Any>(callback: DiffUtil.ItemCallback<T> = BaseDiffCallback()) : ListAdapter<T, BaseViewHolder<VB>>(callback) {

    override fun onBindViewHolder(holder: BaseViewHolder<VB>, position: Int) {
        bind(holder.binding, position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        getViewHolder(parent, viewType)

    open fun getViewHolder(parent: ViewGroup, viewType: Int) =
        BaseViewHolder(createBinding(parent, viewType))

    abstract fun createBinding(parent: ViewGroup, viewType: Int): VB

    protected abstract fun bind(binding: VB, position: Int)

    val mList: MutableList<T> = mutableListOf()

    fun setList(list: List<T>) {
        mList.clear()
        mList.addAll(list)
        submitList(mList.toMutableList())
        notifyDataSetChanged()
    }

    fun updateList(list: List<T>) {
        mList.addAll(list)
        submitList(mList)
    }
}

open class BaseDiffCallback<T : Any> : DiffUtil.ItemCallback<T>() {
    override fun areItemsTheSame(oldItem: T, newItem: T): Boolean = oldItem == newItem

    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(oldItem: T, newItem: T): Boolean = oldItem == newItem
}

open class BaseViewHolder<out T : ViewBinding>(val binding: T) : RecyclerView.ViewHolder(binding.root)