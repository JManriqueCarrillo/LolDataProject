package com.jmanrique.loldataproject.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jmanrique.loldataproject.data.local.model.DBConstants
import com.jmanrique.loldataproject.domain.entities.ChampionSummary
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

@Dao
interface DragonDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertChampionSummaryList(list: List<ChampionSummary>): Completable

    @Query("SELECT * FROM " + DBConstants.CHAMPION_SUMMARY_TABLE_NAME + " ORDER BY " + DBConstants.CHAMPION_SUMMARY_PRIMARY_KEY + " desc")
    fun getChampionSummary(): Single<List<ChampionSummary>>

}