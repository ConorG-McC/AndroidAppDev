package com.example.androidappdev.presentation.screens.team.add

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.androidappdev.data.entities.Team
import java.util.UUID

class AddTeamViewModel : ViewModel() {
    private var _teamName = MutableLiveData(String())
    val teamName: LiveData<String> = _teamName
    fun onTeamNameChange(teamName: String) {
        _teamName.value = teamName
    }

    private fun allDataIsValid(): Boolean {
        return _teamName.value!!.isNotBlank()
    }

    fun add() {
        if (allDataIsValid()) {
            val newTeam = Team(UUID.randomUUID(), _teamName.value.toString()
            )
            clear()
            Log.d("NEW TEAM", newTeam.toString())
        }
    }

    private fun clear() {
        _teamName.value = String()
    }
}