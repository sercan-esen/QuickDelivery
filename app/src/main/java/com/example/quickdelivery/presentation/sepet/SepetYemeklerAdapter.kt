package com.example.quickdelivery.presentation.sepet

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.quickdelivery.BaseApp
import com.example.quickdelivery.databinding.SepetYemekRowBinding
import com.example.quickdelivery.domain.model.SepettekiYemekDto

import com.squareup.picasso.Picasso
class SepetYemeklerAdapter(private val listener: (Int) -> Unit) : RecyclerView.Adapter<SepetYemeklerAdapter.ViewHolder>() {
    private var sepetList = ArrayList<SepettekiYemekDto>()

    inner class ViewHolder(view: SepetYemekRowBinding) : RecyclerView.ViewHolder(view.root) {
        val rootView = view
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SepetYemeklerAdapter.ViewHolder {
        return ViewHolder(
            SepetYemekRowBinding.inflate(
                LayoutInflater.from(BaseApp.applicationContext()),
                parent,
                false
            )
        )

    }



    override fun onBindViewHolder(holder: SepetYemeklerAdapter.ViewHolder, position: Int) {
        val yemek = sepetList.get(position)

        holder.rootView.tvSepetYemekAdi.text = yemek.yemek_adi
        holder.rootView.tvSepetYemekFiyati.text = "${yemek.yemek_fiyat}₺"
        holder.rootView.tvSepetYemekAdet.text = "x${yemek.yemek_siparis_adet} Adet"



        holder.rootView.ivSilResim.setOnClickListener {
            listener(yemek.sepet_yemek_id.toInt())
        }


        val url = "http://kasimadalan.pe.hu/yemekler/resimler/${yemek.yemek_resim_adi}"
        Picasso.get().load(url).into(holder.rootView.ivSepetYemek)
    }

    override fun getItemCount(): Int {
        return sepetList.size
    }

    fun updateList(list: ArrayList<SepettekiYemekDto>) {
        sepetList = list
        notifyDataSetChanged()
    }
}