package com.jmanrique.loldataproject.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.jmanrique.loldataproject.data.local.DragonDAO
import com.jmanrique.loldataproject.domain.entities.ChampionSummary

@Database(entities = arrayOf(ChampionSummary::class), version = 1)
@TypeConverters(Converters::class)
abstract class DragonDatabase : RoomDatabase() {
    abstract fun getDragonDao(): DragonDAO
}