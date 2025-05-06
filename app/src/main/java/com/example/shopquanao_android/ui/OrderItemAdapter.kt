package com.example.shopquanao_android.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Button
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.shopquanao_android.R

data class OrderItem(val imageResId: Int, val status: String,val name : String, val address : String ,val price: String )

class OrderItemAdapter(private val items: List<OrderItem>) : RecyclerView.Adapter<OrderItemAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val status : TextView = itemView.findViewById(R.id.status)
        val imageView: ImageView = itemView.findViewById(R.id.order_item_imageView)
        val name: TextView = itemView.findViewById(R.id.order_item_name)
        val address: TextView = itemView.findViewById(R.id.adress)
        val price: TextView = itemView.findViewById(R.id.order_item_price)
        val button :Button  = itemView.findViewById(R.id.btn_buy)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.order_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.imageView.setImageDrawable(
            ResourcesCompat.getDrawable(holder.imageView.resources, item.imageResId, null)
        )
        holder.status.text = item.status
        holder.name.text = item.name
        holder.address.text = item.address
        holder.price.text = item.price
        holder.button.setOnClickListener {
            // Xử lý sự kiện khi nút "Mua lại" được nhấn

        }


    }

    override fun getItemCount(): Int = items?.size ?:0
}