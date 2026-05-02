package com.example.goldapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.example.goldapp.model.GoldModel
import com.example.goldapp.ui.theme.GoldAppTheme
import com.example.goldapp.userInterface.GoldPage


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GoldAppTheme {
                // Seçilen altını tutacağımız değişken. Null ise liste gösterilir.
                var selectedGold by remember { mutableStateOf<GoldModel?>(null) }

                Scaffold(modifier = Modifier.fillMaxSize()) { _ ->
                    // Eğer bir altın seçildiyse Detay Sayfasını aç
                    if (selectedGold != null) {
                        GoldDetailScreen(
                            gold = selectedGold!!,
                            onBack = { selectedGold = null }
                        )
                    } else {
                        // Seçili altın yoksa Ana Listeyi göster
                        GoldPage(
                            onGoldClick = { clickedGold ->
                                selectedGold = clickedGold
                            }
                        )
                    }
                }
            }
        }
    }
}