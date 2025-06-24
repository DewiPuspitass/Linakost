package com.dewipuspitasari0020.linakost.screen

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.dewipuspitasari0020.linakost.navigation.Screen
import com.dewipuspitasari0020.linakost.ui.theme.LinakostTheme
import com.dewipuspitasari0020.linakost.ui.theme.bg
import com.dewipuspitasari0020.linakost.ui.theme.bgSecondary

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(navController: NavHostController) {
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
        ScreenContent(Modifier.padding(innerPadding))
    }
}

@Composable
fun ScreenContent(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Hallo Kamu berhasil Login"
        )
        Log.d("SuccessLogin", "Berhasil login")
    }
}

@Preview
@Composable
private fun MainScreenPreview() {
    LinakostTheme {
        MainScreen(rememberNavController())
    }
}