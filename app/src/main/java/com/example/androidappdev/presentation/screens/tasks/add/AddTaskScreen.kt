package com.example.androidappdev.presentation.screens.tasks.add

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
fun AddTaskScreen(
        text: String,
        modifier: Modifier = Modifier,
        navController: NavHostController,
        vm: AddTaskViewModel = viewModel(factory = AddTaskViewModel.Factory),
) {
    val title: String by vm.title.observeAsState("")
    val description: String by vm.description.observeAsState("")
    val keyboardController = LocalSoftwareKeyboardController.current

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
        Column(modifier = Modifier.align(Alignment.CenterHorizontally)) {
            CustomTextField(stringResource(R.string.title_hint),
                            text = title,
                            onNameChange = { vm.onTitleChange(it) },
                            stringResource(R.string.title_error_message),
                            !vm.title.value.isNullOrBlank()
            )
            CustomTextField(stringResource(R.string.description_hint),
                            text = description,
                            onNameChange = { vm.onDescriptionChange(it) },
                            stringResource(R.string.description_error_message),
                            !vm.description.value.isNullOrBlank()
            )

            Row(horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
            ) {
                CustomButton(stringResource(R.string.add), clickButton = {
                    vm.add()
                    keyboardController?.hide()
                    navController.navigate("Tasks")
                })
            }

            Row(horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
            ) {
                CustomButton(stringResource(R.string.close),
                             clickButton = { navController.navigate("Tasks") })
            }
        }
    }
}
