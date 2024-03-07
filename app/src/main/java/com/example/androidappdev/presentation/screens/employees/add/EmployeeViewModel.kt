package com.example.androidappdev.presentation.screens.employees.add

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.androidappdev.data.Employee
import java.util.UUID

class EmployeeViewModel : ViewModel() {
    private var _firstName = MutableLiveData(String())
    val firstName: LiveData<String> = _firstName

    fun onFirstNameChange(firstName: String) {
        _firstName.value = firstName
    }

    private var _surname = MutableLiveData(String())
    val surname: LiveData<String> = _surname

    fun onSurnameChange(description: String) {
        _surname.value = description
    }

    fun allDataIsValid(): Boolean {
        return _firstName.value!!.isNotBlank()
                && _surname.value!!.isNotBlank()
    }

    fun add() {
        if (allDataIsValid()) {
            val newEmployee = Employee(
                UUID.randomUUID(),
                _firstName.value.toString(),
                _surname.value.toString()
            )
            clear()

            Log.d("NEW EMPLOYEE", newEmployee.toString())

        }
    }

    private fun clear() {
        _firstName.value = String()
        _surname.value = String()
    }
}