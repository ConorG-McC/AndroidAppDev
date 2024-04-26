package com.example.androidappdev.presentation.screens.tasks.edit

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.absoluteOffset
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.androidappdev.R
import com.example.androidappdev.data.task.Task
import com.example.androidappdev.data.task.TaskStatus
import com.example.androidappdev.presentation.components.BottomNavBar
import com.example.androidappdev.presentation.components.CustomButton
import com.example.androidappdev.presentation.components.CustomTextField

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun EditTaskScreen(modifier: Modifier = Modifier,
                   navController: NavHostController,
                   vm: EditTaskViewModel = viewModel(factory = EditTaskViewModel.Factory),
                   selectedTask: Task,
                   onClickToHome: () -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    val taskStatusList = TaskStatus.entries
    var selectedStatus by remember { mutableStateOf(selectedTask.status) }

    LaunchedEffect(key1 = Unit) {//Called on launch
        vm.setSelectedTask(selectedTask)
    }
    Scaffold(modifier = modifier,
             bottomBar = { BottomNavBar(navController = navController) }) {
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
                                isPassword = false,
                                onNameChange = { vm.title = it },
                                stringResource(R.string.title_error_message),
                                vm.titleIsValid()
                )

                CustomTextField(stringResource(R.string.description_hint),
                                text = vm.description,
                                isPassword = false,
                                onNameChange = { vm.description = it },
                                stringResource(R.string.description_error_message),
                                vm.descriptionIsValid()
                )

                Box(modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .fillMaxWidth(), contentAlignment = Alignment.Center
                ) {
                    androidx.compose.material3.Text(
                        selectedStatus.status,
                        modifier = Modifier
                            .clickable { expanded = true }
                            .padding(horizontal = 10.dp)
                            .background(Color.DarkGray,
                                        RoundedCornerShape(10.dp)
                            )
                            .padding(10.dp)
                            .clip(RoundedCornerShape(10.dp)),
                        fontSize = 20.sp,
                        color = Color.White,
                    )
                    Box(modifier = Modifier.absoluteOffset(y = 10.dp,
                                                           x = (-40).dp
                    )
                    ) {
                        DropdownMenu(expanded,
                                     onDismissRequest = { expanded = false }) {
                            taskStatusList.forEach { status ->
                                DropdownMenuItem(
                                    text = {
                                        androidx.compose.material3.Text(status.status,
                                                                        fontSize = 20.sp
                                        )
                                    },
                                    onClick = {
                                        selectedStatus = status
                                        vm.onStatusChange(status)
                                        expanded = false
                                    },
                                    modifier = Modifier.padding(horizontal = 10.dp),
                                )
                            }
                        }
                    }
                }

                CustomButton(stringResource(R.string.save), clickButton = {
                    vm.updateTask()
                    onClickToHome()
                })

            }
        }
    }
}
