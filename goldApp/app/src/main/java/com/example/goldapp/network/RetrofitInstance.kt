package com.example.goldapp.network
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    // Sadece ana domain kısmı yeterli
    private const val BASE_URL = "https://api.collectapi.com/"

    val api: GoldApi by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(GoldApi::class.java)
    }
}
