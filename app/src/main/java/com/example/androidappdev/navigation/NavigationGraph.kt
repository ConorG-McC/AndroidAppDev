package com.example.androidappdev.navigation

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.androidappdev.R
import com.example.androidappdev.presentation.screens.EmployeeScreen
import com.example.androidappdev.presentation.screens.TasksScreen
import com.example.androidappdev.presentation.screens.HomeScreen
import com.example.androidappdev.presentation.screens.LoginScreen
import com.example.androidappdev.presentation.screens.TeamScreen
import kotlin.system.exitProcess

open class NavScreen(var icon:Int, var route:String){
    object Login : NavScreen(R.drawable.home, "Login")
    object Home : NavScreen(R.drawable.home, "Home")
    object Team : NavScreen(R.drawable.team, "Team")
    object Employees : NavScreen(R.drawable.employees, "Employees")
    object Tasks: NavScreen(R.drawable.tasks, "Tasks")
    object Exit: NavScreen(R.drawable.logout, "Logout")
}

@Composable
fun NavigationGraph(
    navController: NavHostController,
    context: Context,
    simulateLogin: () -> Unit,
    modifier: Modifier
) {
    NavHost(navController,
        startDestination = NavScreen.Login.route) {
        composable(NavScreen.Login.route) {
            LoginScreen(stringResource(R.string.login_button), simulateLogin, modifier)
        }
        composable(NavScreen.Home.route) {
            HomeScreen(stringResource(R.string.home_button), context, modifier)
        }
        composable(NavScreen.Team.route) {
            TeamScreen(stringResource(R.string.team_button),context, modifier)
        }
        composable(NavScreen.Employees.route) {
            EmployeeScreen(stringResource(R.string.employee_button),context, modifier)
        }
        composable(NavScreen.Tasks.route) {
            TasksScreen(stringResource(R.string.tasks_button), modifier)
        }
        composable(NavScreen.Exit.route) {
            exitProcess(0)
        }
    }
}
