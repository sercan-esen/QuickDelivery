package com.example.quickdelivery.presentation.odeme

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.example.quickdelivery.R
import com.example.quickdelivery.databinding.FragmentOdemeBinding
import com.google.android.material.snackbar.Snackbar
import dagger.BindsInstance

class OdemeFragment : Fragment() {
    private lateinit var binding: FragmentOdemeBinding
    private lateinit var paymentBillDetails: PaymentBillDetails

    val args: OdemeFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOdemeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getArgs()
        val fiyat = paymentBillDetails.productPrice.toString()
        binding.tvPaymentPrice.text="${fiyat} ₺"

        binding.btnOde.setOnClickListener {
            if (binding.etNameSurname.text.isEmpty()) {
                Snackbar.make(binding.root, "Ad Soyad Giriniz", Snackbar.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (!binding.etCreditCardNumber.isDone) {
                Snackbar.make(binding.root, "Kredi Karti No Giriniz", Snackbar.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (!binding.etCreditCardExpireDate.isDone) {
                Snackbar.make(
                    binding.root,
                    "Kredi Karti Son Kullanma Tarihi Giriniz",
                    Snackbar.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }
            if (!binding.etCcv.isDone) {
                Snackbar.make(binding.root, "CCV Giriniz", Snackbar.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            processPayment()
            Snackbar.make(binding.root, "İşlem Başarılı", Snackbar.LENGTH_SHORT).show()
        }
    }

    private fun processPayment() {
        binding.tvCreditCardExpireDate.text = binding.etCreditCardExpireDate.unMasked
        binding.tvCreditCardNo.text = binding.etCreditCardNumber.unMasked
        binding.tvNameSurname.text = binding.etNameSurname.text.toString().toUpperCase()
        if(binding.etCreditCardNumber.unMasked.get(0).toInt() % 2 == 0) {
            binding.ivCC.setImageResource(R.drawable.ic_visa)
        } else {
            binding.ivCC.setImageResource(R.drawable.ic_mastercard)
        }
    }

    private fun getArgs() {
        paymentBillDetails = args.paymentdetails
    }
}