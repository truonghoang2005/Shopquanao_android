package com.example.shopquanao_android.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Button
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.shopquanao_android.Firebase.FirebaseHelper
import com.example.shopquanao_android.R
import com.example.shopquanao_android.model.Order

data class OrderItem(val imageResId: Int, val status: String,val name : String, val address : String ,val price: String )

class OrderItemAdapter(private val items: List<Order>, private val contextImage: Context, private val removeOrder: (String)->Unit) : RecyclerView.Adapter<OrderItemAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txtStatus : TextView = itemView.findViewById(R.id.txtStatus)
        val imgProduct: ImageView = itemView.findViewById(R.id.imgProduct)
        val txtName: TextView = itemView.findViewById(R.id.txtName)
        val txtAddress: TextView = itemView.findViewById(R.id.txtAddress)
        val txtPrice: TextView = itemView.findViewById(R.id.txtPrice)
        val btnHuydon :Button  = itemView.findViewById(R.id.btnHuydon)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.order_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        val product = item.orderItem?.get(0)
        val idImage = product?.photo?.let { FirebaseHelper.getDrawableId(contextImage, it) }
        if (idImage != null) {
           holder.imgProduct.setImageResource(idImage)
        }
        if(item.status == "Pending"){
            holder.txtStatus.text = "Đang giao"
        }else {
            holder.txtStatus.text = "Đã giao"
        }
        holder.txtAddress.text ="Nơi nhận hàng: " +item.address
        holder.txtName.text = product?.product_name
        holder.txtPrice.text = item.totalPrice.toString()+"$"
        holder.btnHuydon.setOnClickListener {
            removeOrder(item.id.toString())
        }
    }

    override fun getItemCount(): Int = items.size
}