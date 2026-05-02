package com.example.goldapp.model
import com.google.gson.annotations.SerializedName

data class GoldResponse(
    val success: Boolean,
    val result: List<GoldModel>
)

data class GoldModel(
    @SerializedName("name")
    val name: String,      // Gram Altın,Çeyrek Altın vb.

    @SerializedName("buying")
    val buy: String,    // Alış fiyatı

    @SerializedName("selling")
    val sell: String    // Satış fiyatı
)