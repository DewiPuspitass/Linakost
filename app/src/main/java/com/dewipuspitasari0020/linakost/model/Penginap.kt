package com.dewipuspitasari0020.linakost.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "penginaps")
data class Penginap (
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val fullName: String,
    val numberRoom: String,
    val address: String,
    val price: Double,
    val checkIn: String,
    val checkOut: String,
    val userId: Int
)