package com.example.quickdelivery.presentation.odeme

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PaymentBillDetails(var productAmount: Int, var productPrice: Double): Parcelable