package com.example.androidappdev.suite

import com.example.androidappdev.components.BottomNavBarTests
import com.example.androidappdev.components.CustomButtonTests
import com.example.androidappdev.components.CustomTextFieldTests
import com.example.androidappdev.components.FloatingActionButtonTests
import com.example.androidappdev.screens.AddTaskScreenTests
import com.example.androidappdev.screens.EditTaskScreenTests
import com.example.androidappdev.screens.HomeScreenTests
import com.example.androidappdev.screens.LoginScreenTests
import com.example.androidappdev.screens.SignUpScreenTests
import com.example.androidappdev.screens.ViewTasksScreenTests
import org.junit.runner.RunWith
import org.junit.runners.Suite

@RunWith(Suite::class)
@Suite.SuiteClasses(
    //Components
    BottomNavBarTests::class,
    CustomButtonTests::class,
    CustomTextFieldTests::class,
    FloatingActionButtonTests::class,
    //Screens
    LoginScreenTests::class,
    SignUpScreenTests::class,
    HomeScreenTests::class,
    ViewTasksScreenTests::class,
    AddTaskScreenTests::class,
    EditTaskScreenTests::class
)
class TestSuite {

}