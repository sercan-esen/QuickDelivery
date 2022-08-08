package com.example.quickdelivery.presentation.sepet

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quickdelivery.domain.model.Resource
import com.example.quickdelivery.domain.repository.SepetRepository
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class SepetViewModel constructor(private val sepetRepository: SepetRepository) : ViewModel() {
    val sepettekiYemekleriGetir = MutableLiveData<SepettekiYemekleriGetirState>()


    fun sepettekiYemekleriGetir(kullanici_adi: String){
        viewModelScope.launch {
            sepettekiYemekleriGetir.postValue(SepettekiYemekleriGetirState(isLoading = true))
            sepetRepository.sepettekiYemekleriGetir(kullanici_adi).apply {
                when (this) {
                    is Resource.Success -> {
                        sepettekiYemekleriGetir.postValue(
                            SepettekiYemekleriGetirState(
                                isLoading = false,
                                error = null,
                                data = this.data
                            )
                        )
                    }
                    else -> {
                        sepettekiYemekleriGetir.postValue(
                            SepettekiYemekleriGetirState(
                                isLoading = false,
                                error = this.message,
                                data = null
                            )
                        )
                    }
                }
            }
        }
    }
    val sepettenYemekSil = MutableLiveData<SepettenYemekSilState>()

    fun sepettenYemekSil(sepet_yemek_id:Int,kullanici_adi:String){
        viewModelScope.launch {
            sepettenYemekSil.postValue(SepettenYemekSilState(isLoading = true))
            sepetRepository.sepettenYemekSil(sepet_yemek_id,kullanici_adi).apply {
                when(this) {
                    is Resource.Success -> {
                        sepettenYemekSil.postValue(
                            SepettenYemekSilState(
                                isLoading = false,
                                error = null,
                                data = this.data
                            )
                        )
                    }

                    else -> {
                        sepettenYemekSil.postValue(
                            SepettenYemekSilState(
                                isLoading = false,
                                error = this.message,
                                data = null,
                            )
                        )

                    }
                }
            }
        }
    }
    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }


}