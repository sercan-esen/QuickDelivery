package com.example.quickdelivery.domain.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class YemekDto(
    @SerializedName("yemek_id")
    val yemek_id: String,
    @SerializedName("yemek_adi")
    val yemek_adi : String,
    @SerializedName("yemek_resim_adi")
    val yemek_resim_adi: String,
    @SerializedName("yemek_fiyat")
    val yemek_fiyat: String
): Parcelable
