package com.example.goldapp.userInterface
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.goldapp.viewmodel.GoldViewModel
import com.example.goldapp.model.GoldModel
import java.text.SimpleDateFormat
import java.util.*

// --- Sürekli kullanılan RENK PALETİ ---
val DeepSpace = Color(0xFF0F172A)
val BorderColor = Color(0xFFE2E8F0)
val LabelGray = Color(0xFF64748B)
val ProGreen = Color(0xFF10B981)
val ProRed = Color(0xFFEF4444)
val AccentGold = Color(0xFFF59E0B)

// fiyatları düzenleme methodu(₺ ekleme . dan sonra 2 basamak çekiyoruz
fun formatPrice(price: String?): String {
    if (price.isNullOrBlank()) return "-"
    return try {
        val cleaned = price.replace(",", ".")
        val value = cleaned.toDouble()
        "%.2f ₺".format(Locale.ENGLISH, value)
    } catch (e: Exception) {
        price ?: "-"
    }
}

@Composable
fun GoldPage(modifier: Modifier = Modifier, onGoldClick: (GoldModel) -> Unit) {
    val viewModel: GoldViewModel = viewModel()
    val isLoading = viewModel.isLoading.value
    val goldList = viewModel.goldList.value

    val fullDate = SimpleDateFormat("dd MMMM yyyy | HH:mm:ss", Locale("tr")).format(Date())

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        // PREMİUM HEADER
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(DeepSpace, Color(0xFF1E293B))
                    )
                )
                .statusBarsPadding() // Edge-to-Edge için burayı ekledim
                .padding(bottom = 24.dp, start = 24.dp, end = 24.dp, top = 16.dp)
        ) {
            Column {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .size(8.dp)
                            .background(AccentGold, RoundedCornerShape(50))
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "CANLI PİYASA",
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        color = AccentGold,
                        letterSpacing = 1.2.sp
                    )
                }
                Text(
                    text = "Altın Borsası",
                    fontSize = 30.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = Color.White
                )
                Text(
                    text = "Son Güncelleme: $fullDate",
                    fontSize = 13.sp,
                    color = Color.White.copy(alpha = 0.5f)
                )
            }
        }

        //     TABLO BAŞLIKLARI
        Surface(
            modifier = Modifier.fillMaxWidth(),
            color = Color.White,
            shadowElevation = 1.dp
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp, vertical = 14.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "VARLIK ADI",
                    modifier = Modifier.weight(1.6f),
                    fontSize = 11.sp,
                    color = LabelGray,
                    fontWeight = FontWeight.Black
                )
                Text(
                    text = "ALIŞ",
                    modifier = Modifier.weight(1f),
                    fontSize = 11.sp,
                    color = LabelGray,
                    fontWeight = FontWeight.Black,
                    textAlign = TextAlign.End
                )
                Spacer(modifier = Modifier.width(32.dp))
                Text(
                    text = "SATIŞ",
                    modifier = Modifier.weight(1f),
                    fontSize = 11.sp,
                    color = LabelGray,
                    fontWeight = FontWeight.Black,
                    textAlign = TextAlign.End
                )
                Spacer(modifier = Modifier.width(28.dp))
            }
        }

        if (isLoading) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator(color = DeepSpace, strokeWidth = 3.dp)
            }
        } else {
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(goldList) { gold ->
                    ProGoldRow(gold = gold) {
                        onGoldClick(gold)
                    }
                    HorizontalDivider(
                        modifier = Modifier.padding(horizontal = 24.dp),
                        thickness = 0.5.dp,
                        color = BorderColor.copy(alpha = 0.5f)
                    )
                }
            }
        }
    }
}
  // canlı verileri  gösterme kısmı dinammik olarak modelviewden aldığımz  listeyi yerleştiriyoruz
@Composable
fun ProGoldRow(gold: GoldModel, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(horizontal = 24.dp, vertical = 20.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(modifier = Modifier.weight(1.6f)) {
            Text(
                text = gold.name ?: "-",
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                color = DeepSpace
            )
        }

        Text(
            text = formatPrice(gold.buy),
            modifier = Modifier.weight(1f),
            fontSize = 12.sp,
            fontWeight = FontWeight.SemiBold,
            color = ProGreen,
            textAlign = TextAlign.End
        )

        Spacer(modifier = Modifier.width(34.dp))

        Text(
            text = formatPrice(gold.sell),
            modifier = Modifier.weight(1f),
            fontSize = 12.sp,
            fontWeight = FontWeight.SemiBold,
            color = ProRed,
            textAlign = TextAlign.End
        )

        Icon(
            imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
            contentDescription = null,
            tint = LabelGray,
            modifier = Modifier
                .padding(start = 12.dp)
                .size(16.dp)
        )
    }
}