package com.example.shopquanao_android.ui.cart

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shopquanao_android.R
import com.example.shopquanao_android.databinding.FragmentCartBinding
import com.example.shopquanao_android.ui.CartItemAdapter
import android.widget.RadioButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.FragmentManager
import androidx.navigation.findNavController
import com.example.shopquanao_android.Firebase.FirebaseHelper
import com.example.shopquanao_android.LoginActivity
import com.google.firebase.auth.FirebaseAuth
import com.example.shopquanao_android.model.CartItem

class CartFragment : Fragment() {

    private var _binding: FragmentCartBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCartBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Dữ liệu mẫu
        val user = FirebaseAuth.getInstance().currentUser
        if(user == null){
            val intent = Intent(this.context, LoginActivity::class.java)
            startActivity(intent)
            findNavController().navigate(R.id.nav_home)
        }
//        val cartItems = listOf(
//            CartItem(R.drawable.avatars,"Ten san pham","gia san pham","thong tin san pham"),
//            CartItem(R.drawable.avatars,"Ten san pham","gia san pham","thong tin san pham"),
//            CartItem(R.drawable.avatars,"Ten san pham","gia san pham","thong tin san pham"),
//            CartItem(R.drawable.avatars,"Ten san pham","gia san pham","thong tin san pham")
//        )
        binding.recyclerView2.apply {
            var list: List<CartItem> = emptyList()
            user?.let {
                FirebaseHelper.fetchCartItems(it.uid, {
                    Itemlist -> list = Itemlist
                    layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                    adapter = CartItemAdapter(list, this.context, {
                        itemId ->
                        setRemoveCartItemButton(itemId, it.uid)
                    })
                },{
                    Toast.makeText(this.context, "Fail", Toast.LENGTH_SHORT).show()
                })
            }
//            if(cartItems==null){
//                val txtNull = findViewById<TextView>(R.id.txtNull)
//                txtNull.visibility = View.GONE
//            }else{
//
//            }
        }
    }

    private fun setRemoveCartItemButton(itemId: String, UserId: String) {
        val alert = this.context?.let { AlertDialog.Builder(it) }
        alert?.apply {
            setTitle("Xóa sản phẩm")
            setMessage("Bạn muốn xóa sản phẩm này khỏi giỏ hàng không?")
            setNegativeButton("No"){ dialogInterface: DialogInterface, i: Int ->
                dialogInterface.dismiss()
            }
            setPositiveButton("Yes"){ dialogInterface: DialogInterface, i: Int ->
                FirebaseHelper.RemoveCartItem(UserId, itemId)
                Toast.makeText(this.context, "Xóa thành công", Toast.LENGTH_SHORT).show()
            }
        }
        alert?.show()
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}