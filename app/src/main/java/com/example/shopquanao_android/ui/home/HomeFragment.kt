package com.example.shopquanao_android.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shopquanao_android.Firebase.FirebaseHelper
import com.example.shopquanao_android.R
import com.example.shopquanao_android.databinding.FragmentHomeBinding
import com.example.shopquanao_android.model.Product
import com.example.shopquanao_android.ui.SliderItem
import com.example.shopquanao_android.ui.ItemAdapter
import com.example.shopquanao_android.ui.SliderAdapter
import com.example.shopquanao_android.ui.cart.CartFragment
import com.google.firebase.database.FirebaseDatabase


class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Dữ liệu mẫu cho slider
//        val sliderItems = listOf(
//            SliderItem(R.drawable.avatars,"Price"),
//            SliderItem(R.drawable.avatars,"Price"),
//            SliderItem(R.drawable.avatars,"Price"),
//            SliderItem(R.drawable.avatars,"Price"),
//            SliderItem(R.drawable.avatars,"Price"),
//            SliderItem(R.drawable.avatars,"Price"),
//            SliderItem(R.drawable.avatars,"Price")
//        )

        //  lập Slider RecyclerView
        setInformationProduct()

//        val gridItems = listOf(
//            Item(R.drawable.avatars, "Product 1"),
//            Item(R.drawable.avatars, "Product 2"),
//            Item(R.drawable.avatars, "Product 3"),
//            Item(R.drawable.avatars, "Product 4"),
//            Item(R.drawable.avatars, "Product 5"),
//            Item(R.drawable.avatars, "Product 6"),
//            Item(R.drawable.avatars, "Product 7"),
//            Item(R.drawable.avatars, "Product 8")
//
//        )



        binding.floatingActionButton2.setOnClickListener {
            findNavController().navigate(R.id.nav_cart)
        }
    }

    private fun setInformationProduct() {
        FirebaseHelper.fetchAllProductData({
                list->
            binding.gridRecyclerView.apply {
                adapter = ItemAdapter(list,{ item ->
                    // Khi nhấn vào item, điều hướng đến ItemDetailFragment và truyền tên sản phẩm
                    val bundle = Bundle().apply {
                        putString("product_id", item.id)
                    }
                    findNavController().navigate(R.id.nav_itemdetail, bundle)
                }, this.context)
            }

            binding.sliderRecyclerView.apply {
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                adapter = SliderAdapter(list,{ item ->
                    val bundle = Bundle().apply {
                        putString("product_id", item.id)
                    }
                    findNavController().navigate(R.id.nav_itemdetail, bundle)
                }, this.context)
            }
        }, {

        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}