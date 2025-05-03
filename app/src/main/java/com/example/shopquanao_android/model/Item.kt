package com.example.shopquanao_android.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Item(
    val id: String = "",
    val imageUrl: String = "",
    val name: String = "",
    val price: Double = 0.0,
    val description: String = "",
    val instructions: String = "",
    val shopName: String = "",
    val categoryIds: List<String> = emptyList()
) : Parcelable