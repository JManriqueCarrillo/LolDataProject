package com.jmanrique.loldataproject.domain.repository

import com.jmanrique.loldataproject.domain.entities.ChampionDetail
import com.jmanrique.loldataproject.domain.entities.ChampionSummary
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

interface DragonRepository {

    fun getCurrentPatch(): Single<String>

    fun saveChampionSummary(data: List<ChampionSummary>): Completable

    fun getChampionSummary(): Single<List<ChampionSummary>>
    fun getChampionDetail(championId: String): Single<ChampionDetail>
}