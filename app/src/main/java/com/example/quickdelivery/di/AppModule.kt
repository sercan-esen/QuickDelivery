package com.example.quickdelivery.di

import androidx.lifecycle.ViewModel
import com.example.quickdelivery.data.remote.ApiService
import com.example.quickdelivery.data.repository.AnasayfaRepositoryImpl
import com.example.quickdelivery.data.repository.SepetRepositoryImpl
import com.example.quickdelivery.domain.repository.AnasayfaRepository
import com.example.quickdelivery.domain.repository.SepetRepository
import com.example.quickdelivery.presentation.anasayfa.AnasayfaViewModel
import com.example.quickdelivery.presentation.detay.DetayViewModel
import com.example.quickdelivery.presentation.sepet.SepetViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val AppModule = module {

    viewModel { AnasayfaViewModel(get()) }
    viewModel { SepetViewModel(get()) }
    viewModel { DetayViewModel(get()) }

    single { provideAnasayfaRepository(get()) }

    single { provideSepetRepository(get()) }
}

fun provideAnasayfaRepository(apiService: ApiService): AnasayfaRepository =
    AnasayfaRepositoryImpl(apiService)

fun provideSepetRepository(apiService: ApiService) : SepetRepository =
    SepetRepositoryImpl(apiService)

