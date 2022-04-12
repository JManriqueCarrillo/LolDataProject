package com.jmanrique.loldataproject.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "sampleEntity")
data class SampleEntity(
    @PrimaryKey
    val id: Int
)