package com.example.shopquanao_android.ui.cart

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shopquanao_android.R
import com.example.shopquanao_android.databinding.FragmentCartBinding
import com.example.shopquanao_android.ui.CartItem
import com.example.shopquanao_android.ui.CartItemAdapter
import android.widget.RadioButton


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
        val cartItems = listOf(
            CartItem(R.drawable.avatars,"Ten san pham","gia san pham","thong tin san pham"),
            CartItem(R.drawable.avatars,"Ten san pham","gia san pham","thong tin san pham"),
            CartItem(R.drawable.avatars,"Ten san pham","gia san pham","thong tin san pham"),
            CartItem(R.drawable.avatars,"Ten san pham","gia san pham","thong tin san pham")

        )

        //  lập Slider RecyclerView
        binding.recyclerView2.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = CartItemAdapter(cartItems)
        }


    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}