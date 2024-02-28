package com.example.androidappdev.presentation.screens


import android.annotation.SuppressLint
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import com.example.androidappdev.navigation.NavigationGraph
import com.example.androidappdev.presentation.components.BottomNavBar

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun OverallDisplay(navController: NavHostController,
                   modifier: Modifier = Modifier) {
    val context = LocalContext.current.applicationContext
    var loginAuthenticated by remember { mutableStateOf(false) }

    Scaffold(
        modifier =modifier,
        bottomBar = {
            BottomNavBar(navController = navController, loginAuthenticated)
        }
    ) {
        NavigationGraph(navController = navController,
                        context,
                        simulateLogin = {loginAuthenticated=true
                            navController.navigate("home")},
                        modifier)
    }
}
