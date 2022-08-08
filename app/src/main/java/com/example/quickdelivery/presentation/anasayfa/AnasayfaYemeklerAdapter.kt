package com.example.quickdelivery.presentation.anasayfa

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.NavDirections
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.quickdelivery.BaseApp
import com.example.quickdelivery.databinding.AnasayfaYemekRowBinding
import com.example.quickdelivery.domain.model.YemekDto
import com.example.quickdelivery.presentation.detay.DetayFragmentDirections
import com.squareup.picasso.Picasso

const val ACTION_SEPETE_EKLE = "ACTION_SEPETE_EKLE"
const val ACTION_DETAYA_GIT = "ACTION_DETAYA_GIT"

class AnasayfaYemeklerAdapter(private val listener: (YemekDto, Int, String) -> Unit) :
    RecyclerView.Adapter<AnasayfaYemeklerAdapter.ViewHolder>() {
    private var yemekList = ArrayList<YemekDto>()

    inner class ViewHolder(view: AnasayfaYemekRowBinding) : RecyclerView.ViewHolder(view.root) {
        val rootView = view
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            AnasayfaYemekRowBinding.inflate(
                LayoutInflater.from(BaseApp.applicationContext()),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val yemek = yemekList.get(position)

        holder.rootView.tvYemekAdi.text = yemek.yemek_adi
        holder.rootView.tvYemekFiyat.text = "${yemek.yemek_fiyat}₺"
        holder.rootView.anasayfaYemekCard.setOnClickListener {
            listener(yemek, 0, ACTION_DETAYA_GIT)
        }

        holder.rootView.ivMiktarArttir.setOnClickListener {
            val miktar = holder.rootView.tvYemekMiktar.text.toString().toInt()
            holder.rootView.tvYemekMiktar.text = (miktar + 1).toString()
        }

        holder.rootView.ivMiktarAzalt.setOnClickListener {
            val miktar = holder.rootView.tvYemekMiktar.text.toString().toInt()
            if (miktar <= 0) return@setOnClickListener
            holder.rootView.tvYemekMiktar.text = (miktar - 1).toString()
        }

        holder.rootView.tvSepeteEkle.setOnClickListener {
            val miktar = holder.rootView.tvYemekMiktar.text.toString().toInt()
            if (miktar == 0) {
                Toast.makeText(BaseApp.applicationContext(), "Adeti Belirtiniz", Toast.LENGTH_LONG)
                    .show()
                return@setOnClickListener
            }
            listener(yemek, miktar, ACTION_SEPETE_EKLE)
        }

        val url = "http://kasimadalan.pe.hu/yemekler/resimler/${yemek.yemek_resim_adi}"
        Picasso.get().load(url).into(holder.rootView.ivYemek)
    }

    override fun getItemCount(): Int {
        return yemekList.size
    }

    fun updateList(list: ArrayList<YemekDto>) {
        yemekList = list
        notifyDataSetChanged()
    }

}