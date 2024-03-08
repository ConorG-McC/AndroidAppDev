package com.example.androidappdev.presentation.screens.team

import android.content.Context
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.androidappdev.presentation.components.FloatingButton


@Composable
fun TeamScreen(text:String,
               context: Context,
               modifier: Modifier = Modifier,
               navController: NavController
) {
    val onClickToAddTeam = {navController.navigate("AddTeam")}

    Column(
        modifier = modifier
    ) {
        Text(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            text = text,
            textAlign = TextAlign.Center,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
        )
        FloatingButton("woop", clickAction = onClickToAddTeam, modifier = modifier)

    }
}