package com.example.androidappdev.data.team

import java.util.UUID

data class Team(
        var id: UUID,
        var teamName: String,
) {
    override fun toString(): String = "Team ID: $id \nTeam Name: $teamName"

}
