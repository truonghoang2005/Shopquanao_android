package com.example.shopquanao_android.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.CheckBox
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.shopquanao_android.R

data class CartItem(val imageResId: Int, val text: String,val text13: String,val text7: String )

class CartItemAdapter(private val items: List<CartItem>) : RecyclerView.Adapter<CartItemAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.cart_item_imageView)
        val textView: TextView = itemView.findViewById(R.id.cart_item_name)
        val textView7: TextView = itemView.findViewById(R.id.cart_item_price)
        val textView13: TextView = itemView.findViewById(R.id.cart_item_info)
        val checkboxButton:CheckBox  = itemView.findViewById(R.id.checkBox)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.cart_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.imageView.setImageDrawable(
            ResourcesCompat.getDrawable(holder.imageView.resources, item.imageResId, null)
        )
        holder.textView.text = item.text
        holder.textView13.text = item.text
        holder.textView7.text = item.text
        holder.checkboxButton.isChecked = position == 0


    }

    override fun getItemCount(): Int = items?.size ?:0
}