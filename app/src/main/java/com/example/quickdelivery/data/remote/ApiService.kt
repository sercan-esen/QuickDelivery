package com.example.quickdelivery.data.remote

import com.example.quickdelivery.domain.model.SepeteYemekEkleDto
import com.example.quickdelivery.domain.model.SepettekiYemekDto
import com.example.quickdelivery.domain.model.SepettekiYemekleriGetirDto
import com.example.quickdelivery.domain.model.TumYemekleriGetirDto
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {


    @GET("yemekler/tumYemekleriGetir.php")
    suspend fun tumYemekleriGetir(): TumYemekleriGetirDto

    @POST("yemekler/sepeteYemekEkle.php")
    @FormUrlEncoded
    suspend fun sepeteYemekEkle(
        @Field("yemek_adi") yemek_adi: String,
        @Field("yemek_resim_adi") yemek_resim_adi: String,
        @Field("yemek_fiyat") yemek_fiyat: Int,
        @Field("yemek_siparis_adet") yemek_siparis_adet: Int,
        @Field("kullanici_adi") kullanici_adi: String
    ): SepeteYemekEkleDto

    @POST("yemekler/sepettekiYemekleriGetir.php")
    @FormUrlEncoded
    suspend fun sepettekiYemekleriGetir (
        @Field("kullanici_adi") kullanici_adi: String): SepettekiYemekleriGetirDto

    @POST("yemekler/sepettenYemekSil.php")
    @FormUrlEncoded
    suspend fun sepettenYemekSil(@Field("sepet_yemek_id") sepet_yemek_id:Int,
                         @Field("kullanici_adi") kullanici_adi:String) : SepettekiYemekDto

}