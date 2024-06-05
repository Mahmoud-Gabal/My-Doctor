package com.example.Doctor.domain.local.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.Doctor.presentation.HomeScreen.DoctorInfo

@Database(
    entities = [DoctorInfo::class,bookmarkedDRs::class],
    version = 1
)
abstract class DoctorDatabase :RoomDatabase(){
    abstract val doctorDao : DoctorDao

    companion object {
        @Volatile
        private var INSTANCE : DoctorDatabase? = null

        fun getInstance(context: Context) : DoctorDatabase {
            return INSTANCE ?: Room.databaseBuilder(
                context.applicationContext,
                DoctorDatabase::class.java,
                "Doctor_Db"
            ).build().also {
                INSTANCE = it
            }
        }
    }
}