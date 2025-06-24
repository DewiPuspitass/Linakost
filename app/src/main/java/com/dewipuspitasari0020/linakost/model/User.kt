package com.dewipuspitasari0020.linakost.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "User")
data class User (
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val fullName: String,
    val email: String,
    val password: String,
    val longitude: Double,
    val latitude: Double
)