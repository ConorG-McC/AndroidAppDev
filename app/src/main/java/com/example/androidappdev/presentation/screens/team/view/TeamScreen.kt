package com.example.androidappdev.presentation.screens.team.view

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
import androidx.navigation.NavController
import com.example.androidappdev.presentation.components.BottomNavBar
import com.example.androidappdev.presentation.components.FloatingButton

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun TeamScreen(text: String,
               modifier: Modifier = Modifier,
               navController: NavController
) {
    val onClickToAddTeam = { navController.navigate("AddTeam") }

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
            FloatingButton("woop",
                           clickAction = onClickToAddTeam,
                           modifier = modifier
            )
        }
    }
}
