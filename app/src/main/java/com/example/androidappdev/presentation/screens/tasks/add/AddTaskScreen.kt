package com.example.androidappdev.presentation.screens.tasks.add

import android.content.res.Resources.Theme
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.absoluteOffset
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.androidappdev.R
import com.example.androidappdev.data.enums.TaskStatus
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
    var expanded by remember { mutableStateOf(false) }
    val taskStatusList = TaskStatus.entries
    var selectedStatus by remember { mutableStateOf(taskStatusList[0]) }

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


            Box(modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .fillMaxWidth(), contentAlignment = Alignment.Center
            ) {
                Text(
                    selectedStatus.status,
                    modifier = Modifier
                        .clickable { expanded = true }
                        .padding(horizontal = 10.dp)
                        .background(Color.DarkGray, RoundedCornerShape(10.dp))
                        .padding(10.dp)
                        .clip(RoundedCornerShape(10.dp)),
                    fontSize = 20.sp,
                    color = Color.White,
                )
                Box(modifier = Modifier.absoluteOffset(y = 10.dp, x = (-40).dp
                )
                ) {
                    DropdownMenu(expanded,
                        onDismissRequest = { expanded = false }) {
                        taskStatusList.forEach { status ->
                            DropdownMenuItem(
                                text = {
                                    Text(status.status, fontSize = 20.sp)
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