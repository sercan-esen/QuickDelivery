package com.example.quickdelivery.data.repository

import com.example.quickdelivery.data.remote.ApiService
import com.example.quickdelivery.domain.exception.traceErrorException
import com.example.quickdelivery.domain.model.Resource
import com.example.quickdelivery.domain.model.SepettekiYemekDto
import com.example.quickdelivery.domain.model.SepettekiYemekleriGetirDto
import com.example.quickdelivery.domain.repository.SepetRepository
import kotlinx.coroutines.CancellationException
import java.lang.Exception

class SepetRepositoryImpl(private val api:ApiService) : SepetRepository {
    override suspend fun sepettekiYemekleriGetir(kullanici_adi: String): Resource<SepettekiYemekleriGetirDto> {
        return try {
            Resource.Success(api.sepettekiYemekleriGetir(kullanici_adi))
        } catch (e: CancellationException) {
            e.printStackTrace()
            Resource.Error(traceErrorException(e).getErrorMessage())
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.Error(traceErrorException(e).getErrorMessage())
        }
    }

    override suspend fun sepettenYemekSil(sepet_yemek_id: Int, kullanici_adi: String): Resource<SepettekiYemekDto> {
        return try {
            Resource.Success(api.sepettenYemekSil(sepet_yemek_id,kullanici_adi))
        } catch (e: CancellationException) {
            e.printStackTrace()
            Resource.Error(traceErrorException(e).getErrorMessage())
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.Error(traceErrorException(e).getErrorMessage())
        }
    }

}

