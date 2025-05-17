package com.example.shopquanao_android.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.CheckBox
import android.widget.ImageButton
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.shopquanao_android.Firebase.FirebaseHelper
import com.example.shopquanao_android.R
import com.example.shopquanao_android.model.CartItem

data class CartItem(val imageResId: Int, val text: String,val text13: String,val text7: String)

class CartItemAdapter(private val items: List<CartItem>, private val contextImage: Context, private val removeItem: (String)-> Unit) : RecyclerView.Adapter<CartItemAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgProduct: ImageView = itemView.findViewById(R.id.imgProduct)
        val txtName: TextView = itemView.findViewById(R.id.txtName)
        val txtPrice: TextView = itemView.findViewById(R.id.txtPrice)
        val txtCategory: TextView = itemView.findViewById(R.id.txtCategory)
        val cbSelect:ImageButton  = itemView.findViewById(R.id.btnDelete)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.cart_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if(items != null){
//
//            holder.imageView.setImageDrawable(
//                ResourcesCompat.getDrawable(holder.imageView.resources, item.imageResId, null)
//            )
//            holder.textView.text = item.text
//            holder.textView13.text = item.text
//            holder.textView7.text = item.text
//            holder.checkboxButton.isChecked = position == 0
            val item = items[position]
            val idImage = item.photo?.let { FirebaseHelper.getDrawableId(contextImage, it) }
            if (idImage != null) {
                holder.imgProduct.setImageResource(idImage)
            }
            holder.txtName.setText(item.product_name)
            holder.txtPrice.setText(item.product_price.toString() + "$")
            holder.txtCategory.setText("quần áo")
            holder.imgProduct.setOnClickListener {
                val bundle = Bundle().apply {
                    putString("product_id", item.product_id)
                }
                holder.itemView.findNavController().navigate(R.id.nav_itemdetail, bundle)
            }
            holder.cbSelect.setOnClickListener {
                removeItem(item.product_id.toString())
            }
        }
    }

    override fun getItemCount(): Int = items?.size ?:0
}