package com.jmanrique.loldataproject.app.di

import com.jmanrique.loldataproject.app.repository.DefaultRepository
import com.jmanrique.loldataproject.data.local.DragonDAO
import com.jmanrique.loldataproject.data.local.preferences.SharedPreferencesManager
import com.jmanrique.loldataproject.data.network.DragonAPI
import com.jmanrique.loldataproject.data.repository.DragonLocalStoreImpl
import com.jmanrique.loldataproject.data.repository.DragonRemoteStoreImpl
import com.jmanrique.loldataproject.data.repository.SharedPreferencesRepositoryImpl
import com.jmanrique.loldataproject.domain.repository.DataStore
import com.jmanrique.loldataproject.domain.repository.DragonRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Singleton
    @Provides
    fun providePreferencesRepository(prefManager: SharedPreferencesManager) =
        SharedPreferencesRepositoryImpl(prefManager)


    @Singleton
    @Provides
    fun providesDragonRepository(
        localStore: DragonLocalStoreImpl,
        remoteStore: DragonRemoteStoreImpl
    ): DragonRepository = DefaultRepository(localStore, remoteStore)

    @Singleton
    @Provides
    fun provideRemoteDragonRepository(api: DragonAPI): DataStore = DragonRemoteStoreImpl(api)

    @Singleton
    @Provides
    fun provideLocalDragonRepository(dao: DragonDAO): DataStore = DragonLocalStoreImpl(dao)

}