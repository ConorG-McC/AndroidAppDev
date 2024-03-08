package com.example.androidappdev.presentation.screens.team.add

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.androidappdev.R
import com.example.androidappdev.presentation.components.CustomButton
import com.example.androidappdev.presentation.components.CustomTextField

@Composable
fun AddTeamScreen(
    text: String,
    modifier: Modifier = Modifier,
    navController: NavHostController,
    vm: AddTeamViewModel = viewModel(),
) {
    val teamName: String by vm.teamName.observeAsState("")
    val keyboardController = LocalSoftwareKeyboardController.current

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
        Column(modifier = Modifier.align(Alignment.CenterHorizontally)) {
            CustomTextField(
                stringResource(R.string.team_name_hint),
                text = teamName,
                onNameChange = { vm.onTeamNameChange(it) },
                stringResource(R.string.team_name_error_message),
                !vm.teamName.value.isNullOrBlank()
            )

            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
            ) {
                CustomButton(stringResource(R.string.add), clickButton = {
                    vm.add()
                    keyboardController?.hide()
                    navController.navigate("Team")
                })
            }

            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
            ) {
                CustomButton(stringResource(R.string.close),
                    clickButton = { navController.navigate("Team") })
            }
        }
    }
}
