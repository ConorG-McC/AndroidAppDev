package com.example.androidappdev.presentation.navigation

import android.content.Context
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.androidappdev.R
import com.example.androidappdev.presentation.screens.employees.view.EmployeeScreen
import com.example.androidappdev.presentation.screens.employees.add.AddEmployeeScreen
import com.example.androidappdev.presentation.screens.tasks.TasksScreen
import com.example.androidappdev.presentation.screens.home.HomeScreen
import com.example.androidappdev.presentation.screens.login.LoginScreen
import com.example.androidappdev.presentation.screens.tasks.add.AddTaskScreen
import com.example.androidappdev.presentation.screens.team.TeamScreen
import com.example.androidappdev.presentation.screens.team.add.AddTeamScreen
import kotlin.system.exitProcess

open class NavScreen(var icon:Int, var route:String){
    data object Login : NavScreen(R.drawable.home, "Login")
    data object Home : NavScreen(R.drawable.home, "Home")
    data object Team : NavScreen(R.drawable.team, "Team")
    data object AddTeam: NavScreen(R.drawable.team, "AddTeam")
    data object Employees : NavScreen(R.drawable.employees, "Employees")
    data object AddEmployee: NavScreen(R.drawable.employees, "AddEmployee")
    data object Tasks: NavScreen(R.drawable.tasks, "Tasks")
    data object AddTask: NavScreen(R.drawable.tasks, "AddTask")
    data object Exit: NavScreen(R.drawable.logout, "Logout")
}

@Composable
fun NavigationGraph(
    navController: NavHostController,
    context: Context,
    simulateLogin: () -> Unit,
    modifier: Modifier = Modifier.testTag("TestNavGraph")
) {
    var selectedEmployeeIndex by remember{ mutableIntStateOf(-1) }

    NavHost(navController,
        startDestination = NavScreen.Login.route) {
        composable(NavScreen.Login.route) {
            LoginScreen(stringResource(R.string.login_button), simulateLogin, modifier)
        }
        composable(NavScreen.Home.route) {
            HomeScreen(stringResource(R.string.home_button), context, modifier)
        }
        composable(NavScreen.Team.route) {
            TeamScreen(stringResource(R.string.team_button),context, modifier, navController)
        }
        composable(NavScreen.AddTeam.route) {
            AddTeamScreen(stringResource(R.string.add_team_button), modifier, navController)
        }
        composable(NavScreen.Employees.route) {
            EmployeeScreen(
                modifier = modifier,
                context= context,
                onIndexChange = {
                    Log.v("OK","index change event called")
                    selectedEmployeeIndex = it
                },
                text = stringResource(R.string.employee_button),
                navController = navController
            )
        }
        composable(NavScreen.AddEmployee.route) {
            AddEmployeeScreen(stringResource(R.string.add_employee_button), modifier, navController)
        }
        composable(NavScreen.Tasks.route) {
            TasksScreen(stringResource(R.string.tasks_button), modifier, navController)
        }
        composable(NavScreen.AddTask.route) {
            AddTaskScreen(stringResource(R.string.add_task_button), modifier, navController)
        }
        composable(NavScreen.Exit.route) {
            exitProcess(0)
        }
    }
}
