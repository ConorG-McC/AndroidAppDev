package com.example.androidappdev.data.`object`

import java.util.UUID

data class Team(
    var id: UUID,
    var teamName: String,
)  {
    override fun toString(): String = "Team ID: $id \nTeam Name: $teamName"

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || javaClass != other.javaClass) return false
        val team = other as Team
        return (team.id == id)
    }
}