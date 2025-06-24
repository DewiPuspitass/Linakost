package com.dewipuspitasari0020.linakost.screen

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.dewipuspitasari0020.linakost.model.Penginap
import com.dewipuspitasari0020.linakost.navigation.Screen
import com.dewipuspitasari0020.linakost.ui.theme.LinakostTheme
import com.dewipuspitasari0020.linakost.ui.theme.bg
import com.dewipuspitasari0020.linakost.ui.theme.bgSecondary
import com.dewipuspitasari0020.linakost.util.ViewModelFactory
import com.dewipuspitasari0020.linakost.viewModel.PenginapViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(navController: NavHostController) {
    val context = LocalContext.current
    val factory = remember { ViewModelFactory(context) }
    val penginapViewModel: PenginapViewModel = viewModel(factory = factory)

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("LinaKost", color = Color.White) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = bg,
                    titleContentColor = Color.White
                ),
                actions = {
                    IconButton(onClick = {
                        Log.d("MainScreen", "Tombol profil diklik!")
                    }) {
                        Icon(
                            imageVector = Icons.Filled.AccountCircle,
                            contentDescription = "Profile",
                            tint = Color.White
                        )
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navController.navigate(Screen.TambahPenginap.route)
                },
                modifier = Modifier.padding(bottom = 32.dp),
                containerColor = bgSecondary,
                contentColor = Color.White
            ) {
                Icon(Icons.Filled.Add, "Tambah Item Baru", tint = bg)
            }
        },
        containerColor = bg
    ) { innerPadding ->
        ScreenContent(
            modifier = Modifier.padding(innerPadding),
            penginapViewModel = penginapViewModel
        )
    }
}

@Composable
fun ScreenContent(modifier: Modifier = Modifier, penginapViewModel: PenginapViewModel) {
    val penginapList by penginapViewModel.penginapList.collectAsStateWithLifecycle()

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (penginapList.isEmpty()) {
            Text(
                text = "Belum ada data penginap. Tambahkan yang baru!",
                color = Color.Gray,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(top = 16.dp)
            )
        } else {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                contentPadding = PaddingValues(bottom = 16.dp)
            ) {
                items(penginapList) { penginap ->
                    PenginapCard(penginap = penginap)
                }
            }
        }
    }
}

@Composable
fun PenginapCard(penginap: Penginap) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFF2C2C2E),
            contentColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(text = "Nama: ${penginap.fullName}", style = MaterialTheme.typography.titleMedium, color = Color.White)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = "Kamar: ${penginap.numberRoom}", style = MaterialTheme.typography.bodyMedium, color = Color.LightGray)
            Text(text = "Alamat: ${penginap.address}", style = MaterialTheme.typography.bodySmall, color = Color.LightGray)
            Text(text = "Harga: Rp${String.format("%,.0f", penginap.price)}", style = MaterialTheme.typography.bodyMedium, color = Color.White)
            Text(text = "Check-in: ${penginap.checkIn}", style = MaterialTheme.typography.bodySmall, color = Color.LightGray)
            Text(text = "Check-out: ${penginap.checkOut}", style = MaterialTheme.typography.bodySmall, color = Color.LightGray)
        }
    }
}

@Preview
@Composable
private fun MainScreenPreview() {
    LinakostTheme {
        MainScreen(rememberNavController())
    }
}