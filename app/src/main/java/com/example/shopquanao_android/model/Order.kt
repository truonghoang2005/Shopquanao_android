package com.example.shopquanao_android.model

class Order(
    var id: String? = null,
    val user_id: String? = null,
    val user_phonenumber: String? = null,
    val address: String? = null,
    val orderItem: List<OrderItem>? = null,
    val totalPrice: Double? = null,
    val status: String? = null
){}