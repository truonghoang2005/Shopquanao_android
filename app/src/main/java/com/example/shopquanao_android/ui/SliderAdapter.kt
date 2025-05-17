package com.example.shopquanao_android.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.shopquanao_android.Firebase.FirebaseHelper
import com.example.shopquanao_android.R
import com.example.shopquanao_android.model.Product

data class SliderItem(val imageResId: Int, val text: String)

class SliderAdapter(private var items: List<Product>, private val onItemClick: (Product) -> Unit, private val conTextimg: Context) : RecyclerView.Adapter<SliderAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//        val imageView: ImageView = itemView.findViewById(R.id.imageView3)
//        val textView: TextView = itemView.findViewById(R.id.textView3)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.slider_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        val item = items[position]
//        holder.imageView.setImageDrawable(
//            ResourcesCompat.getDrawable(holder.imageView.resources, item.imageResId, null)
//        )
//        holder.textView.text = item.text
//        holder.itemView.setOnClickListener {
//            onItemClick(item)
//        }

        holder.itemView.apply {
            val imgProduct = findViewById<ImageView>(R.id.imgProduct)
            val txtName = findViewById<TextView>(R.id.txtName)
            val idImage = items[position].photo?.let { FirebaseHelper.getDrawableId(conTextimg, it) }
            if (idImage != null) {
                imgProduct.setImageResource(idImage)
            }
            txtName.setText(items[position].name)
            imgProduct.setOnClickListener{
                onItemClick(items[position])
            }
        }
    }

    override fun getItemCount(): Int = items.size

    fun updateList(newList: List<Product>) {
        items = newList
        notifyDataSetChanged()
    }
}