package com.example.androidappdev.presentation.navigation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.androidappdev.R
import com.example.androidappdev.core.MyAppApplication
import com.example.androidappdev.data.employee.Employee
import com.example.androidappdev.data.task.Task
import com.example.androidappdev.presentation.screens.employees.add.AddEmployeeScreen
import com.example.androidappdev.presentation.screens.employees.edit.EditEmployeeScreen
import com.example.androidappdev.presentation.screens.employees.view.EmployeeScreen
import com.example.androidappdev.presentation.screens.home.HomeScreen
import com.example.androidappdev.presentation.screens.login.LoginScreen
import com.example.androidappdev.presentation.screens.signup.SignUpScreen
import com.example.androidappdev.presentation.screens.tasks.add.AddTaskScreen
import com.example.androidappdev.presentation.screens.tasks.edit.EditTaskScreen
import com.example.androidappdev.presentation.screens.tasks.view.TasksScreen

open class NavScreen(var icon: Int, var route: String) {
    data object Login : NavScreen(R.drawable.home, "Login")
    data object SignUp : NavScreen(R.drawable.home, "SignUp")
    data object ForgotPassword : NavScreen(R.drawable.home, "ForgotPassword")
    data object Home : NavScreen(R.drawable.home, "Home")
    data object Employees : NavScreen(R.drawable.employees, "Employees")
    data object AddEmployee : NavScreen(R.drawable.employees, "AddEmployee")
    data object EditEmployee : NavScreen(R.drawable.employees, "EditEmployee")
    data object Tasks : NavScreen(R.drawable.tasks, "Tasks")
    data object EditTask : NavScreen(R.drawable.tasks, "EditTask")
    data object AddTask : NavScreen(R.drawable.tasks, "AddTask")
    data object Exit : NavScreen(R.drawable.logout, "Logout")
}

@Composable
fun NavigationGraph(navController: NavHostController,
                    modifier: Modifier = Modifier.testTag("TestNavGraph")
) {

    var selectedTask: Task? = null
    var selectedEmployee: Employee? = null


    NavHost(navController, startDestination = NavScreen.Login.route
    ) {
        composable(NavScreen.Login.route) {
            LoginScreen(
                navigateToSignUpScreen = {
                    navController.navigate(NavScreen.SignUp.route)
                },
                navigateToHomeScreen = {
                    navController.navigate(NavScreen.Home.route)
                },
            )
        }
        composable(NavScreen.SignUp.route) {
            SignUpScreen(navigateBack = { navController.navigate(NavScreen.Login.route) })

        }
        composable(NavScreen.Home.route) {
            HomeScreen(stringResource(R.string.home_button),
                       navController,
                       modifier
            )
        }
        composable(NavScreen.Employees.route) {
            EmployeeScreen(modifier = modifier,
                           onIndexChange = {
                               Log.v("OK", "index change event called")
                               selectedEmployee = it
                           },
                           text = stringResource(R.string.employee_button),
                           navController = navController
            )
        }
        composable(NavScreen.AddEmployee.route) {
            AddEmployeeScreen(stringResource(R.string.add_employee_button),
                              modifier,
                              navController
            )
        }
        composable(NavScreen.EditEmployee.route) {
            EditEmployeeScreen(selectedEmployee = selectedEmployee!!,
                               onClickToHome = {
                                   if (selectedEmployee != null) navController.navigate(
                                       "Employees"
                                   )
                               })
        }
        composable(NavScreen.Tasks.route) {
            TasksScreen(modifier = modifier,
                        onIndexChange = {
                            Log.v("OK", "index change event called")
                            selectedTask = it
                        },
                        text = stringResource(R.string.tasks_button),
                        navController = navController
            )
        }
        composable(NavScreen.EditTask.route) {
            EditTaskScreen(modifier,
                           navController,
                           selectedTask = selectedTask!!,
                           onClickToHome = {
                               if (selectedTask != null) {
                                   navController.navigate("Tasks"
                                   )
                               }
                           })
        }
        composable(NavScreen.AddTask.route) {
            AddTaskScreen(stringResource(R.string.add_task_button),
                          modifier,
                          navController
            )
        }
        composable(NavScreen.Exit.route) {
            // TODO - BUG - Logout is not clearing the UI state causing next user to see the previous user's data before refresh
            navController.navigate(NavScreen.Login.route)
            MyAppApplication.container.authRepository.signOut()
//            exitProcess(0)
        }
    }
}
