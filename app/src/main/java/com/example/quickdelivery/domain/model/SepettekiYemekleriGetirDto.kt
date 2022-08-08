package com.example.quickdelivery.domain.model

import com.google.gson.annotations.SerializedName

data class SepettekiYemekleriGetirDto(
    @SerializedName("sepet_yemekler")
    val sepettekiYemekler: ArrayList<SepettekiYemekDto>,
    @SerializedName("success")
    val success: Int
)