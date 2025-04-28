package com.example.shopquanao_android.ui.itemdetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shopquanao_android.R
import com.example.shopquanao_android.databinding.FragmentItemDetailBinding
import com.example.shopquanao_android.ui.SliderItemDetail
import com.example.shopquanao_android.ui.SliderItemDetailAdapter
import com.example.shopquanao_android.ui.cart.CartFragment
import com.example.shopquanao_android.ui.Item
import com.example.shopquanao_android.ui.ItemAdapter

class ItemDetailFragment : Fragment() {

    private var _binding: FragmentItemDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentItemDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Lấy tên sản phẩm từ Bundle
        val productName = arguments?.getString("product_name") ?: "Unknown Product"
        binding.productName.text = productName

        // Dữ liệu mẫu cho slider
        val sliderItemsDetail = listOf(
            SliderItemDetail(R.drawable.avatars),
            SliderItemDetail(R.drawable.avatars),
            SliderItemDetail(R.drawable.avatars),
            SliderItemDetail(R.drawable.avatars),
            SliderItemDetail(R.drawable.avatars),
            SliderItemDetail(R.drawable.avatars),
            SliderItemDetail(R.drawable.avatars)
        )

        // Thiết lập Slider RecyclerView
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = SliderItemDetailAdapter(sliderItemsDetail)
        }

        val relativeItems = listOf(
            Item(R.drawable.avatars, "Product 1"),
            Item(R.drawable.avatars, "Product 2"),
            Item(R.drawable.avatars, "Product 3"),
            Item(R.drawable.avatars, "Product 4"),
            Item(R.drawable.avatars, "Product 1"),
            Item(R.drawable.avatars, "Product 2"),
            Item(R.drawable.avatars, "Product 3"),
            Item(R.drawable.avatars, "Product 4")
        )
        binding.recyclerView3.apply {
            adapter = ItemAdapter(relativeItems) { item ->
                // Khi nhấn vào item, điều hướng đến ItemDetailFragment và truyền tên sản phẩm
                val bundle = Bundle().apply {
                    putString("product_name", item.text)
                }
                findNavController().navigate(R.id.nav_itemdetail, bundle)
            }
        }
        // Xử lý sự kiện nhấn FloatingActionButton (giỏ hàng - chuyển sang CartFragment)
        binding.floatingActionButton3.setOnClickListener {
            findNavController().navigate(R.id.nav_cart)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}