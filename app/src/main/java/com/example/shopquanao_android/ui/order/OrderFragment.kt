package com.example.shopquanao_android.ui.order

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shopquanao_android.Firebase.FirebaseHelper
import com.example.shopquanao_android.R
import com.example.shopquanao_android.databinding.FragmentOrderBinding
import com.example.shopquanao_android.ui.OrderItemAdapter
import com.example.shopquanao_android.LoginActivity
import com.google.firebase.auth.FirebaseAuth


class OrderFragment : Fragment() {

    private var _binding: FragmentOrderBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOrderBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val user = FirebaseAuth.getInstance().currentUser
        if(user == null){
            val intent = Intent(this.context, LoginActivity::class.java)
            startActivity(intent)
            findNavController().navigate(R.id.nav_home)
        }
//        val orderItems = listOf(
//            OrderItem(R.drawable.avatars,"Đã giao hàng","Tên sản phẩm","Địa chỉ", " giá"),
//            OrderItem(R.drawable.avatars,"Đã giao hàng","Tên sản phẩm","Địa chỉ", " giá"),
//            OrderItem(R.drawable.avatars,"Đã giao hàng","Tên sản phẩm","Địa chỉ", " giá"),
//            OrderItem(R.drawable.avatars,"Đã giao hàng","Tên sản phẩm","Địa chỉ", " giá"),
//
//        )
        if (user != null) {
            FirebaseHelper.fetchOrderbyUserID(user.uid, {
                orders ->
                binding.recyclerView.apply{
                    layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                    adapter = this.context?.let { OrderItemAdapter(orders, it, { orderId ->
                        setButtonHuydon(orderId)
                    }) }
                }
            }, {
                Toast.makeText(this.context, "Lỗi tải trang", Toast.LENGTH_SHORT).show()
            })
        }





    }

    private fun setButtonHuydon(orderId: String) {
        val alert = this.context?.let { AlertDialog.Builder(it) }
        alert?.apply {
            setTitle("Hủy đơn")
            setMessage("Bạn có chắc chắn muốn hủy đơn hàng này không?")
            setNegativeButton("No"){ dialogInterface: DialogInterface, i: Int ->
                dialogInterface.dismiss()
            }
            setPositiveButton("Yes"){ dialogInterface: DialogInterface, i: Int ->
                FirebaseHelper.RemoveOrder(orderId)
                Toast.makeText(this.context, "Hủy đơn thành công", Toast.LENGTH_SHORT).show()
            }
        }
        alert?.show()
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}