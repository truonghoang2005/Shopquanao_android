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

data class Item(val imageResId: Int, val text: String, val price : Double)

class ItemAdapter(private var items: List<Product>, private val onItemClick: (Product) -> Unit, private val contextImage: Context) : RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {

    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
//    {
//        val imgProduct: ImageView = itemView.findViewById(R.id.imgProduct)
//        val txtName: TextView = itemView.findViewById(R.id.txtName)
//    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)
        return ItemViewHolder(view)
    }


    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
//        val item = items[position]
//        holder.imageView.setImageDrawable(
//            ResourcesCompat.getDrawable(holder.imageView.resources, item.imageResId, null)
//        )
//        holder.textView.text = item.text
//        holder.itemView.setOnClickListener {
//            onItemClick(item)
//        }
        holder.itemView.apply {
            val imgProduct: ImageView = findViewById(R.id.imgProduct)
            val txtName: TextView = findViewById(R.id.txtName)
            val txtProductPrice: TextView = findViewById(R.id.txtProductPrice)
            val idImage = items[position].photo?.let { FirebaseHelper.getDrawableId(contextImage, it) }
            if (idImage != null) {
                imgProduct.setImageResource(idImage)
            }
            txtName.setText(items[position].name)
            txtProductPrice.setText(items[position].price.toString())
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