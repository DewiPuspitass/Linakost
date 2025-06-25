package com.dewipuspitasari0020.linakost

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.dewipuspitasari0020.linakost.navigation.SetupNavGraph
import com.dewipuspitasari0020.linakost.ui.theme.LinakostTheme
import com.dewipuspitasari0020.linakost.ui.theme.logobg
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LinakostTheme {
                SetupNavGraph()
            }
        }
    }
}

@Composable
fun SplashScreen1(navController: NavController) {
    LaunchedEffect(key1 = true) {
        delay(3000L)
        navController.navigate("loginscreen") {
            popUpTo("splashScreen") { inclusive = true }
        }
    }

    Column (
        modifier = Modifier.fillMaxSize().background(logobg),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Image(
            painter = painterResource(id = R.drawable.logoo),
            contentDescription = "Logo",
            modifier = Modifier.width(300.dp).height(300.dp)
        )
    }
}
