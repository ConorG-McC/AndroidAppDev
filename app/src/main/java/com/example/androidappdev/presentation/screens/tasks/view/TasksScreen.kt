package com.example.androidappdev.presentation.screens.tasks.view

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
import com.example.androidappdev.presentation.components.BottomNavBar
import com.example.androidappdev.presentation.components.CustomButton
import com.example.androidappdev.presentation.components.FloatingButton
import com.example.androidappdev.utils.Utils.Companion.getStatusColor

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun TasksScreen(
        modifier: Modifier = Modifier,
        vm: TaskViewModel = viewModel(factory = TaskViewModel.Factory),
        onIndexChange: (Task?) -> Unit,
        text: String,
        navController: NavController,
) {
    val context = LocalContext.current
    val onClickToAddTask = { navController.navigate("AddTask") }

    Scaffold(bottomBar = { BottomNavBar(navController = navController) },
             content = {
                 Box(modifier = modifier
                     .fillMaxSize()
                     .padding(16.dp)) {
                     Column(modifier = modifier.fillMaxSize(),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Top
                     ) {
                         Row(modifier = Modifier
                             .fillMaxWidth()
                             .padding(bottom = 16.dp),
                             horizontalArrangement = Arrangement.SpaceBetween,
                             verticalAlignment = Alignment.CenterVertically
                         ) {
                             Text(text = text,
                                  textAlign = TextAlign.Start,
                                  fontSize = 24.sp,
                                  fontWeight = FontWeight.Bold,
                                  color = Color.Black
                             )
                             FloatingButton("Add new task",
                                            clickAction = onClickToAddTask,
                                            modifier = Modifier
                             )
                         }

                         Spacer(modifier = Modifier.height(2.dp))
                         Text(text = "Your Tasks",
                              fontSize = 20.sp,
                              fontWeight = FontWeight.Medium,
                              color = Color.DarkGray,
                              textAlign = TextAlign.Center,
                              modifier = Modifier.fillMaxWidth()
                         )
                         Spacer(modifier = Modifier.height(1.dp))

                         val userState by vm.userState.collectAsState()
                         if (userState.data.isNotEmpty()) {
                             LazyColumnWithSelection(vm, onIndexChange)

                             Spacer(modifier = Modifier.height(12.dp))

                             CustomButton(stringResource(R.string.edit),
                                          clickButton = {
                                              if (!vm.taskHasBeenSelected()) {
                                                  Toast.makeText(context,
                                                                 R.string.no_selection,
                                                                 Toast.LENGTH_SHORT
                                                  ).show()
                                              } else {
                                                  navController.navigate("EditTask")
                                              }
                                          })

                             Spacer(modifier = Modifier.height(3.dp))

                             CustomButton(stringResource(R.string.delete),
                                          clickButton = {
                                              if (!vm.taskHasBeenSelected()) {
                                                  Toast.makeText(context,
                                                                 R.string.no_selection,
                                                                 Toast.LENGTH_SHORT
                                                  ).show()
                                              } else {
                                                  vm.deleteTask()
                                              }
                                          })
                         } else {
                             Text(text = stringResource(R.string.task_screen_default_message),
                                  fontSize = 16.sp,
                                  fontWeight = FontWeight.Normal,
                                  color = Color.Gray,
                                  textAlign = TextAlign.Center,
                                  modifier = Modifier.fillMaxWidth()
                             )
                         }
                     }
                 }
             })
}

@Composable
fun LazyColumnWithSelection(vm: TaskViewModel, onIndexChange: (Task) -> Unit) {
    var selectedIndexToHighlight by remember { mutableIntStateOf(-1) }

    LazyColumn(modifier = Modifier.padding(vertical = 20.dp)) {
        itemsIndexed(vm.userState.value.data) { index, item ->
            ItemView(index = index,
                     item = item!!,
                     selected = selectedIndexToHighlight == index,
                     onClick = { index: Int ->
                         selectedIndexToHighlight =
                             index // local state for highlighting selected item
                         onIndexChange(item)
                         vm.selectedTask = item
                     })
        }
    }
}

@Composable
fun ItemView(index: Int, item: Task, selected: Boolean, onClick: (Int) -> Unit
) {
    val backgroundColor =
        if (selected) MaterialTheme.colors.secondary else Color.Transparent

    Row(modifier = Modifier
        .clickable { onClick.invoke(index) }
        .background(backgroundColor)
        .fillMaxWidth()
        .padding(10.dp)
        .clip(RoundedCornerShape(10.dp))
        .background(MaterialTheme.colors.surface)
        .padding(10.dp),
        verticalAlignment = Alignment.CenterVertically) {
        Icon(imageVector = Icons.Default.Create,
             contentDescription = null,
             modifier = Modifier
                 .size(40.dp)
                 .padding(end = 8.dp),
             tint = MaterialTheme.colors.primary
        )
        Column {
            Text(text = item.title ?: "",
                 fontSize = 18.sp,
                 fontWeight = FontWeight.Bold,
                 color = Color.Black
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = item.description ?: "",
                 fontSize = 14.sp,
                 fontWeight = FontWeight.Normal,
                 color = Color.DarkGray
            )
            Spacer(modifier = Modifier.height(8.dp))
            Box(modifier = Modifier
                .background(getStatusColor(item.status),
                            RoundedCornerShape(5.dp)
                )
                .padding(horizontal = 8.dp, vertical = 4.dp)
            ) {
                Text(text = item.status.status,
                     fontSize = 12.sp,
                     fontWeight = FontWeight.Bold,
                     color = Color.White
                )
            }
        }
    }
}

