package com.example.androidappdev.presentation.screens.employees.view

import android.content.Context
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.androidappdev.presentation.components.FloatingButton


@Composable
fun EmployeeScreen(modifier: Modifier = Modifier,
                   vm: EmployeeViewModel = viewModel(factory = EmployeeViewModel.Factory),
                   context: Context,
                   onIndexChange: (Int) -> Unit,
                   text:String,
                   navController: NavController,
){
    Log.v("TEST","EmployeeScreen called")

    val onClickToAddEmployee = { navController.navigate("AddEmployee") }


    Box(modifier = modifier) {
        Column {
            Text(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .fillMaxWidth(),
                text = text,
                textAlign = TextAlign.Center,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
            )

            LazyColumnWithSelection(vm, onIndexChange)
        }

        FloatingButton(
            "woop",
            clickAction = onClickToAddEmployee,
            modifier = modifier
        )
    }
}

@Composable
fun LazyColumnWithSelection(vm: EmployeeViewModel, onIndexChange: (Int) -> Unit){
    var selectedIndex by remember { mutableIntStateOf(-1) }
    LazyColumn(modifier = Modifier.padding( vertical = 20.dp)) {
        itemsIndexed(vm.items) { index, item ->
            ItemView(
                index = index,
                item = item.toString(),
                selected = selectedIndex == index,
                onClick = {
                    index: Int ->
                    selectedIndex = index //local state for highlighting selected item
                    onIndexChange(selectedIndex)
                    vm.selectedEmployeeIndex = index
                }
            )
        }
    }
}

@Composable
fun ItemView(index: Int,
             item: String,
             selected: Boolean,
             onClick: (Int) -> Unit){
    Text(
        text = item,
        modifier = Modifier
            .clickable {
                onClick.invoke(index)
            }
            .background(if (selected) MaterialTheme.colors.secondary else Color.Transparent)
            .fillMaxWidth()
            .padding(10.dp)
    )
}