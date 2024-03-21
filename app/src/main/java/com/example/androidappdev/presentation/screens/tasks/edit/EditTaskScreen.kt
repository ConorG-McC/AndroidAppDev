package com.example.androidappdev.presentation.screens.tasks.edit

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.androidappdev.R
import com.example.androidappdev.data.task.Task
import com.example.androidappdev.presentation.components.CustomButton
import com.example.androidappdev.presentation.components.CustomTextField

@Composable
fun EditTaskScreen(vm: EditTaskViewModel = viewModel(factory = EditTaskViewModel.Factory),
                   selectedTask: Task,
                   onClickToHome: () -> Unit
) {

    LaunchedEffect(key1 = Unit) {//Called on launch
        vm.setSelectedTask(selectedTask)
    }

    Column(modifier = Modifier.fillMaxSize()
    ) {
        Text(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            text = "Edit",
            textAlign = TextAlign.Center,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
        )
        Column {
            CustomTextField(stringResource(R.string.title_hint),
                            text = vm.title,
                            onNameChange = { vm.title = it },
                            stringResource(R.string.title_error_message),
                            vm.titleIsValid()
            )

            CustomTextField(stringResource(R.string.description_hint),
                            text = vm.description,
                            onNameChange = { vm.description = it },
                            stringResource(R.string.description_error_message),
                            vm.descriptionIsValid()
            )

            CustomButton(stringResource(R.string.save), clickButton = {
                vm.updateTask()
                onClickToHome()
            })

        }
    }
}
