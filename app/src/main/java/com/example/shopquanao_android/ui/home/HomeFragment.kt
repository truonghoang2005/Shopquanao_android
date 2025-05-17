package com.example.shopquanao_android.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shopquanao_android.Firebase.FirebaseHelper
import com.example.shopquanao_android.R
import com.example.shopquanao_android.databinding.FragmentHomeBinding
import com.example.shopquanao_android.model.Product
import com.example.shopquanao_android.testactivity
import com.example.shopquanao_android.ui.ItemAdapter
import com.example.shopquanao_android.ui.SliderAdapter
import com.google.firebase.database.FirebaseDatabase

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var itemAdapter: ItemAdapter
    private lateinit var sliderAdapter: SliderAdapter
    private var originalProductList: List<Product> = emptyList() // Lưu danh sách gốc

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        itemAdapter = ItemAdapter(emptyList(), { item ->
            val bundle = Bundle().apply {
                putString("product_id", item.id)
            }
            findNavController().navigate(R.id.nav_itemdetail, bundle)
        }, requireContext())

        sliderAdapter = SliderAdapter(emptyList(), { item ->
            val bundle = Bundle().apply {
                putString("product_id", item.id)
            }
            findNavController().navigate(R.id.nav_itemdetail, bundle)
        }, requireContext())


        binding.gridRecyclerView.apply {
            adapter = itemAdapter
        }

        binding.sliderRecyclerView.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = sliderAdapter
        }

        // Lấy dữ liệu sản phẩm từ Firebase
        setInformationProduct()

        // Thiết lập nút điều hướng đến giỏ hàng
        binding.floatingActionButton2.setOnClickListener {
//            val intent = Intent(this.context, testactivity::class.java)
//            startActivity(intent)
           findNavController().navigate(R.id.nav_cart)
        }

        // Thiết lập SearchView
        setupSearchView()
    }

    private fun setInformationProduct() {
        FirebaseHelper.fetchAllProductData({ list ->
            // Lưu danh sách gốc
            originalProductList = list

            // Cập nhật danh sách ban đầu
            itemAdapter.updateList(list)
            sliderAdapter.updateList(list) // Slider giữ danh sách gốc
        }, {
            Toast.makeText(context, "Lỗi khi lấy dữ liệu sản phẩm", Toast.LENGTH_SHORT).show()
        })
    }

    private fun setupSearchView() {
        binding.searchView.setOnQueryTextListener(object : androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                filterProducts(newText.orEmpty())
                return true
            }
        })
    }

    private fun filterProducts(query: String) {
        val filteredList = if (query.isEmpty()) {
            originalProductList
        } else {
            originalProductList.filter { product ->
                product.name!!.lowercase().contains(query.lowercase())
            }
        }

        // Chỉ cập nhật gridRecyclerView, sliderRecyclerView giữ nguyên
        itemAdapter.updateList(filteredList)

        // Hiển thị thông báo nếu không có kết quả
//        if (filteredList.isEmpty() && query.isNotEmpty()) {
//            Toast.makeText(context, "Không tìm thấy sản phẩm", Toast.LENGTH_SHORT).show()
//        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}