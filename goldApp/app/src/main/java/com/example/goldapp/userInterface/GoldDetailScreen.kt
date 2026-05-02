package com.example.goldapp

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.goldapp.model.GoldModel
import com.example.goldapp.userInterface.AccentGold
import com.example.goldapp.userInterface.DeepSpace
import com.example.goldapp.userInterface.LabelGray
import com.example.goldapp.userInterface.ProGreen
import com.example.goldapp.userInterface.ProRed
import com.example.goldapp.userInterface.formatPrice
import java.util.Locale

@Composable
fun GoldDetailScreen(gold: GoldModel, onBack: () -> Unit) {

    var amountText by remember { mutableStateOf("1") }


    val buyPrice = gold.buy?.replace(",", ".")?.toDoubleOrNull() ?: 0.0
    val sellPrice = gold.sell?.replace(",", ".")?.toDoubleOrNull() ?: 0.0


    val amount = amountText.toDoubleOrNull() ?: 0.0


    val totalBuy = buyPrice * amount
    val totalSell = sellPrice * amount

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF8F9FA))
    ) {
        //  ÜST BAR (HEADER)
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(DeepSpace)
                .statusBarsPadding()
                .padding(horizontal = 16.dp, vertical = 16.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                IconButton(onClick = { onBack() }) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Geri",
                        tint = Color.White
                    )
                }
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "${gold.name} Detayı",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }
        }

        // GÜNCEL FİYAT KARTLARI
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Alış Kartı
            Card(
                modifier = Modifier.weight(1f),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(text = "ALIŞ FİYATI", fontSize = 10.sp, color = LabelGray, fontWeight = FontWeight.Bold)
                    Text(text = formatPrice(gold.buy), fontSize = 18.sp, fontWeight = FontWeight.ExtraBold, color = ProGreen)
                }
            }

            // Satış Kartı
            Card(
                modifier = Modifier.weight(1f),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(text = "SATIŞ FİYATI", fontSize = 10.sp, color = LabelGray, fontWeight = FontWeight.Bold)
                    Text(text = formatPrice(gold.sell), fontSize = 18.sp, fontWeight = FontWeight.ExtraBold, color = ProRed)
                }
            }
        }

        //    HESAPLAMA ALANI
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = "HESAPLAMA", fontSize = 12.sp, color = LabelGray, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(8.dp))


            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {

                OutlinedTextField(
                    value = amountText,
                    onValueChange = { newValue ->
                        if (newValue.isEmpty() || newValue.matches(Regex("^\\d*\\.?\\d*$"))) {
                            amountText = newValue
                        }
                    },
                    label = { Text("Miktar (Adet/Gr)") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                    modifier = Modifier.weight(1f),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = AccentGold,
                        focusedLabelColor = AccentGold
                    ),
                    singleLine = true,

                    trailingIcon = {
                        if (amountText.isNotEmpty()) {
                            IconButton(onClick = { amountText = "" }) {
                                Icon(
                                    imageVector = Icons.Default.Clear,
                                    contentDescription = "Temizle",
                                    tint = LabelGray
                                )
                            }
                        }
                    }
                )


                Button(
                    onClick = { amountText = "1" },
                    colors = ButtonDefaults.buttonColors(containerColor = DeepSpace),
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier.height(56.dp) 
                ) {
                    Text(text = "Sıfırla", fontWeight = FontWeight.Bold)
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Sonuç Kartı
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = DeepSpace)
            ) {
                Column(modifier = Modifier.padding(20.dp)) {
                    Text(text = "Toplam Karşılık", color = AccentGold, fontSize = 12.sp, fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.height(12.dp))

                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                        Text(text = "Senin Alış Maliyetin:", color = Color.LightGray, fontSize = 14.sp)
                        Text(text = "%.2f ₺".format(Locale.ENGLISH, totalSell), color = Color.White, fontWeight = FontWeight.Bold, fontSize = 16.sp)
                    }

                    HorizontalDivider(modifier = Modifier.padding(vertical = 12.dp), color = Color.Gray.copy(alpha = 0.3f))

                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                        Text(text = "Senin Satış Kazancın:", color = Color.LightGray, fontSize = 14.sp)
                        Text(text = "%.2f ₺".format(Locale.ENGLISH, totalBuy), color = Color.White, fontWeight = FontWeight.Bold, fontSize = 16.sp)
                    }
                }
            }
        }
    }
}