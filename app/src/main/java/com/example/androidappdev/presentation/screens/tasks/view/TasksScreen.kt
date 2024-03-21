package com.example.androidappdev.presentation.screens.tasks.view

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.androidappdev.R
import com.example.androidappdev.data.task.Task
import com.example.androidappdev.presentation.components.CustomButton
import com.example.androidappdev.presentation.components.FloatingButton

@Composable
fun TasksScreen(
    modifier: Modifier = Modifier,
    vm: TaskViewModel = viewModel(factory = TaskViewModel.Factory),
    context: Context,
    onIndexChange: (Task?) -> Unit,
    text: String,
    navController: NavController,
) {
    val context = LocalContext.current
    val onClickToAddTask = { navController.navigate("AddTask") }

    Box(modifier = modifier) {
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
            val userState by vm.userState.collectAsState()
            if (userState.data.isNotEmpty())
                LazyColumnWithSelection(vm, onIndexChange)

            CustomButton(stringResource(R.string.edit), clickButton = {
                if (!vm.taskHasBeenSelected()) {
                    (Toast.makeText(context,
                                    R.string.no_selection,
                                    Toast.LENGTH_SHORT
                    )).show()
                } else {
                    navController.navigate("EditTask")
                }
            })

            CustomButton(stringResource(R.string.delete), clickButton = {
                if (!vm.taskHasBeenSelected()) {
                    (Toast.makeText(
                        context, R.string.no_selection, Toast.LENGTH_SHORT
                    )).show()
                } else {
                    vm.deleteTask()
                }
            })
        }
    }

    FloatingButton(
        "woop", clickAction = onClickToAddTask, modifier = modifier
    )
}


@Composable
fun LazyColumnWithSelection(vm: TaskViewModel,
                            onIndexChange: (Task) -> Unit) {

    var selectedIndexToHighlight by remember { mutableStateOf(-1) }


    LazyColumn(modifier = Modifier.padding(vertical = 20.dp)) {
        itemsIndexed(vm.userState.value.data) { index, item ->
            ItemView(index = index,
                item = item.toString(),
                selected = selectedIndexToHighlight == index,
                onClick = { index: Int ->
                    selectedIndexToHighlight =
                        index //local state for highlighting selected item
                    onIndexChange(item!!)
                    vm.selectedTask = item
                })
        }
    }
}

@Composable
fun ItemView(
    index: Int, item: String, selected: Boolean, onClick: (Int) -> Unit
) {
    Text(text = item, modifier = Modifier
        .clickable {
            onClick.invoke(index)
        }
        .background(if (selected) MaterialTheme.colors.secondary else Color.Transparent)
        .fillMaxWidth()
        .padding(10.dp))
}
