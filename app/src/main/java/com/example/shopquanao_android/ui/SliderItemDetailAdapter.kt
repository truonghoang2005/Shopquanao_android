package com.example.shopquanao_android.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.shopquanao_android.R

data class SliderItemDetail(val imageResId: Int)

class SliderItemDetailAdapter(private val items: List<SliderItemDetail>) : RecyclerView.Adapter<SliderItemDetailAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.imageView4)

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.slider_item_detail, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.imageView.setImageDrawable(
            ResourcesCompat.getDrawable(holder.imageView.resources, item.imageResId, null)
        )
    }

    override fun getItemCount(): Int = items.size
}