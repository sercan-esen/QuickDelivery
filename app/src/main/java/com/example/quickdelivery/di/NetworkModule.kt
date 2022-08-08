package com.example.quickdelivery.di

import com.example.quickdelivery.BuildConfig
import com.example.quickdelivery.data.remote.ApiService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

private const val TIME_OUT = 15L

val NetworkModule = module {

    single { provideApiService(get()) }
    single { provideRetrofit(get()) }
    single { provideOkHttpClient(get()) }
    single { provideLoggingInceptor() }
}

fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit =
    Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

fun provideOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
    val httpClient = OkHttpClient.Builder()
    httpClient.addInterceptor(httpLoggingInterceptor)
    httpClient.connectTimeout(TIME_OUT, TimeUnit.SECONDS)
    httpClient.readTimeout(TIME_OUT, TimeUnit.SECONDS)
    return httpClient.build()
}

fun provideLoggingInceptor(): HttpLoggingInterceptor {
    val httpLoggingInterceptor = HttpLoggingInterceptor()
    httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
    return httpLoggingInterceptor
}

fun provideApiService(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)