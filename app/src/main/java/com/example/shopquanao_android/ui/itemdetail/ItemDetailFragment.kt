package com.example.shopquanao_android.ui.itemdetail

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shopquanao_android.CheckoutActivity
import com.example.shopquanao_android.Firebase.FirebaseHelper
import com.example.shopquanao_android.LoginActivity
import com.example.shopquanao_android.R
import com.example.shopquanao_android.databinding.FragmentItemDetailBinding
import com.example.shopquanao_android.model.CartItem
import com.example.shopquanao_android.model.Product
import com.example.shopquanao_android.ui.SliderItemDetail
import com.example.shopquanao_android.ui.SliderItemDetailAdapter
import com.example.shopquanao_android.ui.cart.CartFragment
import com.example.shopquanao_android.ui.Item
import com.example.shopquanao_android.ui.ItemAdapter
import com.google.firebase.auth.FirebaseAuth

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
        val productId = arguments?.getString("product_id") ?: "0"
        setProductInformation(productId)
        setInformationSlide()
        setButtonAddcart(productId)
        setButtonBuy(productId)


        binding.floatingActionButton3.setOnClickListener {
            findNavController().navigate(R.id.nav_cart)
        }
    }

    private fun setButtonBuy(productId: String) {
        binding.btnMua.setOnClickListener {
            val intent = Intent(this.context, CheckoutActivity::class.java)
            intent.putExtra("product_id", productId)
            startActivity(intent)
        }
    }

    private fun setButtonAddcart(productId: String) {
        binding.btnAddCart.setOnClickListener {
            FirebaseHelper.fetchProductByID(productId, {
                    product ->
                var cartItem = CartItem(product.id, product.name, product.photo, product.price)
                FirebaseAuth.getInstance().currentUser?.let { it1 -> FirebaseHelper.saveCartItem(it1.uid, cartItem) }
                Toast.makeText(this.context, "Thêm vào giỏ hàng thành công", Toast.LENGTH_SHORT).show()
            })
        }
    }

//    private fun setOnClickFloatButton(Id: String) {
//        binding.floatingActionButton3.setOnClickListener {
//            val user = FirebaseAuth.getInstance().currentUser
//            if(user == null){
//                val intent = Intent(this.context, LoginActivity::class.java)
//                startActivity(intent)
//            }else{
//                FirebaseHelper.fetchProductByID(Id, { product->
//                    val itemCart = CartItem(product.id, product.name, product.photo, product.price)
//                    FirebaseHelper.saveCartItem(user.uid, itemCart)
//                    Toast.makeText(this.context, "Thêm thành công", Toast.LENGTH_SHORT).show()
//                })
//            }
//        }
//    }

    private fun setInformationSlide() {
        FirebaseHelper.fetchAllProductData({
                list ->
            binding.recyclerView3.apply {
                adapter = ItemAdapter(list,{ item ->
                    // Khi nhấn vào item, điều hướng đến ItemDetailFragment và truyền tên sản phẩm
                    val bundle = Bundle().apply {
                        putString("product_id", item.id)
                    }
                    findNavController().navigate(R.id.nav_itemdetail, bundle)
                }, this.context)
            }
        }, {

        })
    }

    private fun setProductInformation(productId: String) {
        FirebaseHelper.fetchProductByID(productId, { product->
            binding.txtName.setText(product.name)
            binding.txtPrice.setText(product.price.toString()+"$")
            binding.txtCategory.setText(product.category)
            binding.txtDesc.setText(product.description)

            val sliderItemsDetail = listOf(
                SliderItemDetail(product.photo.toString()),
                SliderItemDetail(product.photo1.toString()),
                SliderItemDetail(product.photo2.toString())
            )

            // Thiết lập Slider RecyclerView
            binding.recyclerView.apply {
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                adapter = SliderItemDetailAdapter(sliderItemsDetail, this.context)
            }
        })

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}