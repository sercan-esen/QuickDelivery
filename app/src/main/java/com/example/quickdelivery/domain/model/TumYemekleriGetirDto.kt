package com.example.quickdelivery.domain.model

import com.google.gson.annotations.SerializedName

data class TumYemekleriGetirDto(
    @SerializedName("yemekler")
    val yemekler: ArrayList<YemekDto>,
    @SerializedName("success")
    val success: Int
)
