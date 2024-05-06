package com.example.mydoctor.domain.DI

import android.app.Application
import com.example.mydoctor.data.local.manager.localUserManagerImp
import com.example.mydoctor.domain.local.manager.localUseManager
import com.example.mydoctor.domain.local.manager.useCases.AppEntryCases
import com.example.mydoctor.domain.local.manager.useCases.ReadAppEntry
import com.example.mydoctor.domain.local.manager.useCases.SaveAppEntry
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

