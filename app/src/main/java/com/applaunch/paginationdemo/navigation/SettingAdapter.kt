package com.applaunch.paginationdemo.navigation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.applaunch.paginationdemo.databinding.ItemListSettingBinding
import com.applaunch.paginationdemo.navigation.model.Result

class SettingAdapter(val movie:List<Result> ): RecyclerView.Adapter<SettingAdapter.ViewHolder>() {

    inner class ViewHolder(var binding:ItemListSettingBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemListSettingBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       holder.binding.tvTitle.text = movie[position].title
       holder.binding.tvContent.text = movie[position].overview
    }

    override fun getItemCount(): Int = movie.size
}