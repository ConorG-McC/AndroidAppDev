package com.example.androidappdev.navigation

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.androidappdev.R
import com.example.androidappdev.presentation.screens.AddScreen
import com.example.androidappdev.presentation.screens.HomeScreen
import com.example.androidappdev.presentation.screens.LoginScreen
import kotlin.system.exitProcess

open class NavScreen(var icon:Int, var route:String){
    object Login : NavScreen(R.drawable.home, "Login")
    object Home : NavScreen(R.drawable.home, "Home")
    object Add: NavScreen(R.drawable.add, "Add")
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
        composable(NavScreen.Add.route) {
            AddScreen(stringResource(R.string.add_button), modifier)
        }
        composable(NavScreen.Exit.route) {
            exitProcess(0)
        }
    }
}
