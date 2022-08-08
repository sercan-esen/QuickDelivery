package com.example.quickdelivery.domain.repository

import com.example.quickdelivery.domain.model.Resource
import com.example.quickdelivery.domain.model.SepettekiYemekDto
import com.example.quickdelivery.domain.model.SepettekiYemekleriGetirDto

interface SepetRepository {

    suspend fun sepettekiYemekleriGetir(kullanici_adi: String):Resource<SepettekiYemekleriGetirDto>

    suspend fun sepettenYemekSil(sepet_yemek_id:Int,kullanici_adi:String): Resource<SepettekiYemekDto>


}