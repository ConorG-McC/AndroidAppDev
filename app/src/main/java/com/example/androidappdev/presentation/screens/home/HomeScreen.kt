package com.example.androidappdev.presentation.screens.home

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.androidappdev.presentation.components.BottomNavBar

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(text: String,
               navController: NavHostController,
               modifier: Modifier = Modifier
) {

    Scaffold(modifier = modifier,
             bottomBar = { BottomNavBar(navController = navController) }) {
        Column(modifier = modifier
        ) {
            Text(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                text = text,
                textAlign = TextAlign.Center,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
            )
        }
    }
}
