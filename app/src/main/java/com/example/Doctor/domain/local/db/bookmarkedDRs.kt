package com.example.Doctor.domain.local.db

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class bookmarkedDRs(
    val name : String ,
    val job : String ,
    val stars : Int ,
    val reviews : Int ,
    val exp : Int ,
    val about : String ,
    val img  : Int ,
    val address  : String,
    @PrimaryKey(autoGenerate = true) val key : Int = 0

)
