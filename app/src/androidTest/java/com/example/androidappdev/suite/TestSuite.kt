package com.example.androidappdev.suite

import com.example.androidappdev.components.BottomNavBarTests
import com.example.androidappdev.components.CustomButtonTests
import com.example.androidappdev.components.CustomTextFieldTests
import com.example.androidappdev.components.FloatingActionButtonTests
import com.example.androidappdev.screens.LoginScreenTests
import com.example.androidappdev.screens.SignUpScreenTests
import org.junit.runner.RunWith
import org.junit.runners.Suite

@RunWith(Suite::class)
@Suite.SuiteClasses(
    BottomNavBarTests::class,
    CustomButtonTests::class,
    CustomTextFieldTests::class,
    FloatingActionButtonTests::class,
    LoginScreenTests::class,
    SignUpScreenTests::class,
//                    HomeScreenTests::class,
//                    AddScreenTests::class,
//                    EditScreenTests::class
)
class TestSuite {

}