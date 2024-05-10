package com.example.Doctor.domain.DI

import android.app.Application

import com.example.Doctor.data.local.manager.localUserManagerImp
import com.example.Doctor.domain.local.manager.localUseManager
import com.example.Doctor.domain.local.manager.useCases.AppEntryCases
import com.example.Doctor.domain.local.manager.useCases.ReadAppEntry
import com.example.Doctor.domain.local.manager.useCases.SaveAppEntry
import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
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


}

