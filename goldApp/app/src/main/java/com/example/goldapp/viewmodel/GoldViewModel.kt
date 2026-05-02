package com.example.goldapp.viewmodel

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.goldapp.model.GoldModel
import com.example.goldapp.network.RetrofitInstance
import kotlinx.coroutines.launch

class GoldViewModel : ViewModel() {

    var goldList = mutableStateOf<List<GoldModel>>(emptyList())
        private set

    var isLoading = mutableStateOf(true)
        private set

    init {
        fetchGoldPrices()
    }

    private fun fetchGoldPrices() {
        viewModelScope.launch {
            try {
                isLoading.value = true
                val response = RetrofitInstance.api.getGoldPrices()

                if (response.success) {
                    goldList.value = response.result

                    // TEST İÇİN LOG EKLİYORUZ herhangi bir hatamız olursa görmek için
                    Log.d("API_TEST", "Harika! Gelen altın çeşidi sayısı: ${response.result.size}")
                    Log.d("API_TEST", "İlk altın verisi: ${response.result[0].name} - Alış: ${response.result[0].buy}")

                } else {
                    Log.e("API_TEST", "API'ye bağlandık ama success: false döndü!")
                }
            } catch (e: Exception) {
                // TEST İÇİN LOG EKLİYORUZ İnternet yoksa veya çökme olursa burası çalışır
                Log.e("API_TEST", "Eyvah, hata oluştu: ${e.localizedMessage}")
                e.printStackTrace()
            } finally {
                isLoading.value = false
            }
        }
    }
}