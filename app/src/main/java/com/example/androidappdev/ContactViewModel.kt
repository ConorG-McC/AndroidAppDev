package com.example.androidappdev

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ContactViewModel : ViewModel() {
    private var _firstName = MutableLiveData(String())
    val firstName: LiveData<String> = _firstName

    fun onFirstNameChange(firstName: String) {
        _firstName.value = firstName
    }

    private var _surname = MutableLiveData(String())
    val surname: LiveData<String> = _surname

    fun onSurnameChange(surname: String) {
        _surname.value = surname
    }

    private var _telNo = MutableLiveData(String())
    val telNo: LiveData<String> = _telNo

    fun onTelNumberChange(telNo: String) {
        _telNo.value = telNo
    }

    fun allDataIsValid(): Boolean {
        return _firstName.value!!.isNotBlank()
                && _surname.value!!.isNotBlank()
                && _telNo.value!!.isNotBlank()
    }

    fun add() {
        if (allDataIsValid()) {
            val newContact = Contact(
                _firstName.value.toString(),
                _surname.value.toString(),
                _telNo.value.toString()
            )
            clear()

            Log.d("Testing toString", newContact.toString())

        }
    }

    private fun clear() {
        _firstName.value = String()
        _surname.value = String()
        _telNo.value = String()
    }
}