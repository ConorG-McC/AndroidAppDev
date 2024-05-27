package com.example.androidappdev.presentation.screens.tasks.add

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.absoluteOffset
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Scaffold
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.androidappdev.R
import com.example.androidappdev.data.task.TaskStatus
import com.example.androidappdev.presentation.components.BottomNavBar
import com.example.androidappdev.presentation.components.CustomButton
import com.example.androidappdev.presentation.components.CustomTextField
import com.example.androidappdev.utils.Utils.Companion.getStatusColor

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AddTaskScreen(
    text: String,
    modifier: Modifier = Modifier,
    navController: NavHostController,
    vm: AddTaskViewModel = viewModel(factory = AddTaskViewModel.Factory),
) {
    val context = LocalContext.current

    val title: String by vm.title.observeAsState("")
    val description: String by vm.description.observeAsState("")
    val keyboardController = LocalSoftwareKeyboardController.current
    var expanded by remember { mutableStateOf(false) }
    val taskStatusList = TaskStatus.entries
    var selectedStatus by remember { mutableStateOf(taskStatusList[0]) }

    Scaffold(
        modifier = modifier,
        bottomBar = { BottomNavBar(navController = navController) }
    ) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = text,
                textAlign = TextAlign.Center,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                CustomTextField(
                    hintText = stringResource(R.string.title_hint),
                    text = title,
                    isPassword = false,
                    onNameChange = { vm.onTitleChange(it) },
                    errorMessage = stringResource(R.string.title_error_message),
                    errorPresent = !vm.title.value.isNullOrBlank()
                )
                Spacer(modifier = Modifier.height(16.dp))
                CustomTextField(
                    hintText = stringResource(R.string.description_hint),
                    text = description,
                    isPassword = false,
                    onNameChange = { vm.onDescriptionChange(it) },
                    errorMessage = stringResource(R.string.description_error_message),
                    errorPresent = !vm.description.value.isNullOrBlank()
                )
                Spacer(modifier = Modifier.height(16.dp))
                Box(
                    modifier = Modifier
                        .width(200.dp)
                        .clickable { expanded = true }
                        .padding(horizontal = 10.dp)
                        .background(getStatusColor(selectedStatus), RoundedCornerShape(10.dp))
                        .padding(10.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .semantics { contentDescription = "StatusDropdown" },
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        selectedStatus.status,
                        fontSize = 20.sp,
                        color = Color.White
                    )
                    Box(
                        modifier = Modifier.absoluteOffset(y = 10.dp, x = (-40).dp)
                    ) {
                        DropdownMenu(
                            expanded = expanded,
                            onDismissRequest = { expanded = false }
                        ) {
                            taskStatusList.forEach { status ->
                                DropdownMenuItem(
                                    text = { Text(status.status, fontSize = 20.sp) },
                                    onClick = {
                                        selectedStatus = status
                                        vm.onStatusChange(status)
                                        expanded = false
                                    },
                                    modifier = Modifier.padding(horizontal = 10.dp).semantics { contentDescription = status.status },
                                )
                            }
                        }
                    }
                }
                Spacer(modifier = Modifier.height(32.dp))
                Row(
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    CustomButton(
                        text = stringResource(R.string.add),
                        clickButton = {
                            if (vm.allDataIsValid()) {
                                vm.add()
                                keyboardController?.hide()
                                navController.navigate("Tasks")
                            } else {
                                Toast.makeText(context, R.string.fields_not_valid, Toast.LENGTH_SHORT).show()
                            }

                        }
                    )
                }
                Spacer(modifier = Modifier.height(1.dp))
                Row(
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    CustomButton(
                        text = stringResource(R.string.close),
                        clickButton = { navController.navigate("Tasks") }
                    )
                }
            }
        }
    }
}
