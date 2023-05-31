package com.applaunch.paginationdemo.pagination.paging

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.applaunch.paginationdemo.databinding.QuoteItemBinding
import com.applaunch.paginationdemo.pagination.interfaces.listenerMinus
import com.applaunch.paginationdemo.pagination.interfaces.listenerPlus
import com.applaunch.paginationdemo.pagination.model.Result

class QuotePagingAdapter: PagingDataAdapter<Result,QuotePagingAdapter.ViewHolder>(COMPARATOR) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            QuoteItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
     val item = getItem(position)
        if (item != null){
           holder.binding.quote.text = item.content
        }

        holder.binding.plus.setOnClickListener {view ->
            item?.let {
                listenerPlus?.invoke(view,position,it)
                holder.binding.message.text = it.count.toString()


            }
        }

        holder.binding.minus.setOnClickListener {view ->
            item?.let {
                listenerMinus?.invoke(view,position,it)
                holder.binding.message.text = it.count.toString()
            }
        }
    }

    inner class ViewHolder(var binding: QuoteItemBinding):RecyclerView.ViewHolder(binding.root)

    companion object{
        private val COMPARATOR = object :DiffUtil.ItemCallback<Result>(){
            override fun areItemsTheSame(oldItem: Result, newItem: Result): Boolean {
               return oldItem._id == newItem._id
            }

            override fun areContentsTheSame(oldItem: Result, newItem: Result): Boolean {
               return oldItem == newItem
            }
        }
    }
}