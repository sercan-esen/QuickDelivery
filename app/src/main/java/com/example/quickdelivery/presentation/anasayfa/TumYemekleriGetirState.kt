package com.example.quickdelivery.presentation.anasayfa

import com.example.quickdelivery.domain.model.TumYemekleriGetirDto

data class TumYemekleriGetirState(
    var isEmittedOnce: Boolean = false,
    val isLoading: Boolean = false,
    val error: String? = null,
    val data: TumYemekleriGetirDto? = null
)
