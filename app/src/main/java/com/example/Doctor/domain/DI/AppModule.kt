package com.example.Doctor.domain.DI

import android.app.Application
import android.content.Context
import androidx.room.Room

import com.example.Doctor.data.local.manager.localUserManagerImp
import com.example.Doctor.data.remote.Data.NewsRepoImpl.NewsRepoImpl
import com.example.Doctor.domain.local.db.DoctorDao
import com.example.Doctor.domain.local.db.DoctorDatabase
import com.example.Doctor.domain.local.manager.localUseManager
import com.example.Doctor.domain.local.manager.useCases.AppEntryCases
import com.example.Doctor.domain.local.manager.useCases.ReadAppEntry
import com.example.Doctor.domain.local.manager.useCases.SaveAppEntry
import com.example.Doctor.domain.remote.NewsRepo
import com.example.Doctor.domain.remote.NewsService
import com.example.Doctor.domain.remote.UseCases.GetNewsCase
import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    @Provides
    @Singleton
    fun provideLocalUserManagerImp(application: Application) : localUseManager{
        return localUserManagerImp(application)
    }

    @Provides
    @Singleton
    fun provideAppEntryCases(localUseManager: localUseManager) : AppEntryCases{
        return AppEntryCases(
            readAppEntry = ReadAppEntry(localUseManager),
            saveAppEntry = SaveAppEntry(localUseManager)
        )
    }

//    @Provides
//    @Singleton
//    fun provideDoctorDatabase(@ApplicationContext context: Context) : DoctorDatabase
//         =
//            Room.databaseBuilder(
//                context,
//                DoctorDatabase::class.java,
//                "Doctor_Db"
//            ).build()




    @Provides
    @Singleton
    fun provideDoctorDao(@ApplicationContext context: Context) : DoctorDao
    = DoctorDatabase.getInstance(context).doctorDao

    @Provides
    @Singleton
    fun provideNewsService() : NewsService = Retrofit.Builder()
        .baseUrl("https://newsapi.org/v2/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(NewsService::class.java)

    @Provides
    @Singleton
    fun privideNewsImpl(newsService: NewsService) : NewsRepo = NewsRepoImpl(newsService)

    @Provides
    @Singleton
    fun provideGetNewsCase(newsRepo: NewsRepo) : GetNewsCase = GetNewsCase(newsRepo)


}

