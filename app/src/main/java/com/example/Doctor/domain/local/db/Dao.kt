package com.example.Doctor.domain.local.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Upsert
import com.example.Doctor.presentation.HomeScreen.DoctorInfo
import kotlinx.coroutines.flow.Flow

@Dao
interface DoctorDao {

    //    for table DoctorInfo
    @Upsert
    suspend fun addDoctor(DoctorInfo: DoctorInfo)

    @Upsert
    suspend fun addListOfDoctor(DoctorsInfo: List<DoctorInfo>)

    @Delete
    suspend fun deleteDoctor(DoctorInfo: DoctorInfo)

    @Query("SELECT * FROM DoctorInfo WHERE name = :name AND address = :address ")
    fun getDoctorsFromNameAndAddress(name: String, address: String): Flow<List<DoctorInfo>>

    @Query("SELECT * FROM DoctorInfo ORDER BY name ")
    fun getDoctors(): Flow<List<DoctorInfo>>





    @Upsert
    suspend fun addBokkmarkDR(bookmarkedDRs: bookmarkedDRs)

    @Upsert
    suspend fun addListOfBookmarkDRS(bookmarkedDRs: List<bookmarkedDRs>)

    @Delete
    suspend fun deleteBookmarkDr(bookmarkedDRs: bookmarkedDRs)

    @Query("SELECT * FROM bookmarkedDRs ORDER BY `key` DESC")
    fun getAllBookemarkDrs() : Flow<List<bookmarkedDRs>>

    @Query("SELECT * FROM bookmarkedDRs WHERE name = :name AND address = :address")
    fun getBookmarkDRsFromNameAndAddress(name: String,address: String) : Flow<List<bookmarkedDRs>>


}