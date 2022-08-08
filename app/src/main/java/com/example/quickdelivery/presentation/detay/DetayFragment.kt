package com.example.quickdelivery.presentation.detay

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.quickdelivery.R
import com.example.quickdelivery.databinding.FragmentDetayBinding
import com.example.quickdelivery.domain.model.YemekDto
import com.example.quickdelivery.presentation.anasayfa.AnasayfaViewModel
import com.google.android.material.snackbar.Snackbar
import com.squareup.picasso.Picasso
import org.koin.androidx.viewmodel.ext.android.viewModel


class DetayFragment : Fragment() {
    private lateinit var binding: FragmentDetayBinding
    private val viewModel: DetayViewModel by viewModel()
    private lateinit var secilenYemek: YemekDto

    val args: DetayFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetayBinding.inflate(inflater, container, false)
        getArgs()
        return binding.root
    }

    private fun getArgs() {
        secilenYemek = args.yemek
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUI()
        observeSepeteYemekEkle()
        sepeteEkleClickListener()
        miktarClickListeners()
        binding.ivGoBack.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun setUI() {
        if(!this::secilenYemek.isInitialized) {
            Snackbar.make(binding.root, "Yemek getirilirken bir hata olustu.", Snackbar.LENGTH_SHORT)
                .show()
            return
        }
        loadImage(secilenYemek.yemek_resim_adi)
        binding.dtYemekAd.text = secilenYemek.yemek_adi
        binding.dtFiyat.text = "${secilenYemek.yemek_fiyat}TL"
    }

    private fun miktarClickListeners() {
        binding.dtMNus.setOnClickListener {
            val adet = binding.dtAdet.text.toString().toInt()
            if (adet == 1) {
                return@setOnClickListener
            }
            binding.dtAdet.text = (adet - 1).toString()
        }
        binding.dtAdd.setOnClickListener {
            val adet = binding.dtAdet.text.toString().toInt()
            binding.dtAdet.text = (adet + 1).toString()
        }
    }

    private fun sepeteEkleClickListener() {
        binding.dtSepetButton.setOnClickListener {
            val adet = binding.dtAdet.text.toString().toInt()

            if (adet == 0) {
                Snackbar.make(binding.root, "Miktar 0 veya daha az olamaz.", Snackbar.LENGTH_SHORT)
                    .show()
                return@setOnClickListener
            }
            sepeteEkle(secilenYemek, adet)
        }
    }

    private fun sepeteEkle(yemek: YemekDto, adet: Int) {
        viewModel.sepeteYemekEkle(
            yemek.yemek_adi,
            yemek.yemek_resim_adi,
            yemek.yemek_fiyat.toInt(),
            adet,
            "sercan_esen"
        )
    }


    private fun observeSepeteYemekEkle() {
        viewModel.sepeteYemekEkle.observe(viewLifecycleOwner, Observer {
            if (it.isEmittedOnce) {
                return@Observer
            }
            if (it.isLoading) {
                //Loading
            } else {
                if (it.error != null) {
                    Toast.makeText(requireContext(), it.error, Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(requireContext(), "Islem Basarili", Toast.LENGTH_SHORT).show()
                }
            }
            viewModel.sepeteYemekEkle.value!!.isEmittedOnce = true
        })
    }

    private fun loadImage(img_path: String) {
        val url = "http://kasimadalan.pe.hu/yemekler/resimler/${img_path}"
        Picasso.get().load(url).into(binding.dtResim)
    }
}