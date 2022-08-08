package com.example.quickdelivery.presentation.anasayfa

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quickdelivery.domain.model.Resource
import com.example.quickdelivery.domain.repository.AnasayfaRepository
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class AnasayfaViewModel constructor(private val anasayfaRepository: AnasayfaRepository) :
    ViewModel() {

    val tumYemekleriGetir = MutableLiveData<TumYemekleriGetirState>()

    fun tumYemekleriGetir() {
        viewModelScope.launch {
            tumYemekleriGetir.postValue(TumYemekleriGetirState(isLoading = true))
            anasayfaRepository.tumYemekleriGetir().apply {
                when (this) {
                    is Resource.Success -> {
                        tumYemekleriGetir.postValue(
                            TumYemekleriGetirState(
                                isLoading = false,
                                error = null,
                                data = this.data
                            )
                        )
                    }
                    else -> {
                        tumYemekleriGetir.postValue(
                            TumYemekleriGetirState(
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

    val sepeteYemekEkle = MutableLiveData<SepeteYemekEkleState>()

    fun sepeteYemekEkle(yemek_adi: String, yemek_resim_adi: String, yemek_fiyati: Int, adet: Int, kullanici_adi: String ){
        viewModelScope.launch {
            sepeteYemekEkle.postValue(SepeteYemekEkleState(isLoading = true))
            anasayfaRepository.sepeteYemekEkle(yemek_adi, yemek_resim_adi, yemek_fiyati, adet, kullanici_adi).apply {
                when (this) {
                    is Resource.Success -> {
                        sepeteYemekEkle.postValue(
                            SepeteYemekEkleState(
                                isLoading = false,
                                error = null,
                                data = this.data
                            )
                        )
                    }
                    else -> {
                        sepeteYemekEkle.postValue(
                            SepeteYemekEkleState(
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
    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }
}