package com.example.goldapp.network

import com.example.goldapp.model.GoldResponse
import retrofit2.http.GET
import retrofit2.http.Headers

interface GoldApi {
    // sorgu göndreceğimiz adres için key
    @Headers(
        "content-type: application/json",
        "authorization: apikey  4uGIyiGbK5pRWBmb7XtY9k:18TogTLip7nTnCW2P0fE3d"
    )

    // sorgu yapan method bilgileri çekiyoruz
    @GET("economy/goldPrice")
    suspend fun getGoldPrices(): GoldResponse
}