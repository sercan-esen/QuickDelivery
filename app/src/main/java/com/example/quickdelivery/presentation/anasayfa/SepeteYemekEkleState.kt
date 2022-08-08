package com.example.quickdelivery.presentation.anasayfa

import com.example.quickdelivery.domain.model.SepeteYemekEkleDto

data class SepeteYemekEkleState(
    var isEmittedOnce: Boolean = false,
    val isLoading: Boolean = false,
    val error: String? = null,
    val data: SepeteYemekEkleDto? = null
)
