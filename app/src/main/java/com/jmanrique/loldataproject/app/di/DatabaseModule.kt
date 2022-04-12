package com.jmanrique.loldataproject.app.di

import android.content.Context
import androidx.room.Room
import com.jmanrique.loldataproject.data.local.database.DragonDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

      @Singleton
    @Provides
    fun providesDatabase(@ApplicationContext appContext: Context): DragonDatabase {
        return Room.databaseBuilder(
            appContext,
            DragonDatabase::class.java,
            "database"
        ).build()
    }

}