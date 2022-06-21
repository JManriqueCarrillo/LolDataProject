package com.jmanrique.loldataproject.data.local.model

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = DBConstants.CHAMPION_SUMMARY_TABLE_NAME)
data class DBChampionSummary(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = DBConstants.CHAMPION_SUMMARY_PRIMARY_KEY)
    val id: Int,
    val alias: String,
    val name: String,
    val roles: List<String>,
    val squarePortraitPath: String
)