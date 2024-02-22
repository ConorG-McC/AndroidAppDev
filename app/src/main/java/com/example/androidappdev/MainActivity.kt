package com.example.androidappdev

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
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
import com.example.androidappdev.ui.theme.AndroidAppDevTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AndroidAppDevTheme {
                OverallDisplay(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            Color.DarkGray
                        )
                )
            }
        }
    }

    @Preview
    @Composable
    fun OverallDisplay(modifier: Modifier = Modifier) {
        var total by remember { mutableIntStateOf(0) }
        var output by remember { mutableStateOf("") }
        var currentValue by remember { mutableStateOf("") }

        val buttonPadding = Modifier.padding(horizontal = 10.dp)

        AndroidAppDevTheme {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                OutputDisplay(textToDisplay = output, modifier)
                Spacer(modifier = Modifier.size(30.dp))

                CustomButton(
                    stringResource(R.string.one), clickButton = {
                        currentValue += "1"
                        output = currentValue
                    }, buttonPadding
                )
                CustomButton(
                    stringResource(R.string.four), clickButton = {
                        currentValue += "4"
                        output = currentValue
                    }, buttonPadding
                )
                CustomButton(
                    stringResource(R.string.seven), clickButton = {
                        currentValue += "7"
                        output = currentValue
                    }, buttonPadding
                )
                CustomButton(
                    stringResource(R.string.add), clickButton = {
                        if (currentValue.isNotEmpty()) total += currentValue.toInt()
                        currentValue = String()
                        output = total.toString()
                    }, buttonPadding
                )
                CustomButton(
                    stringResource(R.string.equals), clickButton = {
                        if (currentValue.isNotEmpty()) total += currentValue.toInt()
                        output = total.toString()
                        currentValue = String()
                        total = 0
                    }, buttonPadding
                )
                CustomButton(stringResource(R.string.cancel), clickButton = {
                    currentValue = String()
                    total = 0
                    output = String()
                }, buttonPadding)

            }

        }
    }


    @Composable
    fun OutputDisplay(textToDisplay: String, modifier: Modifier = Modifier) {
        Row(
            horizontalArrangement = Arrangement.Center, modifier = modifier
        ) {
            Text(
                textToDisplay,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                fontSize = 30.sp
            )
        }
    }

    @Composable
    fun CustomButton(
        text: String, clickButton: () -> Unit, modifier: Modifier = Modifier
    ) {
        Button(
            onClick = clickButton,
            shape = RoundedCornerShape(10.dp),
            modifier = modifier
        ) {
            Text(text = text, fontSize = 20.sp)
        }
        Spacer(
            modifier = Modifier.size(30.dp)
        )
    }
}



