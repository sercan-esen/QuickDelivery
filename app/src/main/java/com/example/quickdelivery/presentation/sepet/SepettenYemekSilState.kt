package com.example.quickdelivery.presentation.sepet

import com.example.quickdelivery.domain.model.SepettekiYemekDto

data class SepettenYemekSilState(
    var isEmittedOnce: Boolean = false,
    val isLoading: Boolean = false,
    val error: String? = null,
    val data: SepettekiYemekDto? = null)