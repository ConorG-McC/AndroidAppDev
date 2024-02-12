package com.example.androidappdev

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                OverallDisplay()
            }
        }
    }

    @Preview
    @Composable
    fun OverallDisplay() {
        var total by remember { mutableIntStateOf(0) }
        Display(textToDisplay = total.toString())
        Spacer(modifier = Modifier.size(30.dp))
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(50.dp)
                .fillMaxWidth()
        ) {
            CustomButton(stringResource(R.string.add_button_text),
                clickButton = { total++ })
            CustomButton(stringResource(R.string.subtract_button_text),
                clickButton = { total-- })
        }
    }

    @Composable
    fun CustomButton(text: String, clickButton: () -> Unit) {
        Button(
            onClick = clickButton,
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier.padding(horizontal = 10.dp)
        ) {
            Text(text = text, fontSize = 20.sp)
        }
    }


    @Composable
    fun Display(textToDisplay: String) {
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.DarkGray)
        ) {
            Text(
                textToDisplay,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                fontSize = 30.sp
            )
        }
    }
}


