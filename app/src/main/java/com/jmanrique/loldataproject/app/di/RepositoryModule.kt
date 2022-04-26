package com.jmanrique.loldataproject.app.di

import com.jmanrique.loldataproject.domain.repository.DataStore
import com.jmanrique.loldataproject.app.repository.DefaultRepository
import com.jmanrique.loldataproject.data.local.DragonDAO
import com.jmanrique.loldataproject.data.network.DragonAPI
import com.jmanrique.loldataproject.data.network.model.mappers.DataMapper
import com.jmanrique.loldataproject.data.repository.DragonLocalStoreImpl
import com.jmanrique.loldataproject.data.repository.DragonRemoteStoreImpl
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
    fun providesDragonRepository(
        localStore: DragonLocalStoreImpl,
        remoteStore: DragonRemoteStoreImpl
    ): DragonRepository = DefaultRepository(localStore, remoteStore)

    @Singleton
    @Provides
    fun provideRemoteDragonRepository(api: DragonAPI, mapper: DataMapper): DataStore = DragonRemoteStoreImpl(api, mapper)

    @Singleton
    @Provides
    fun provideDataMapper(): DataMapper = DataMapper()

    @Singleton
    @Provides
    fun provideLocalDragonRepository(dao: DragonDAO): DataStore = DragonLocalStoreImpl(dao)

}