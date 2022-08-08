package com.example.quickdelivery.presentation.sepet


import com.example.quickdelivery.domain.model.SepettekiYemekleriGetirDto

data class SepettekiYemekleriGetirState(
    var isEmittedOnce: Boolean = false,
    val isLoading: Boolean = false,
    val error: String? = null,
    val data: SepettekiYemekleriGetirDto? = null
)
