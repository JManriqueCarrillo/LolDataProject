package com.jmanrique.loldataproject.domain.repository

import com.jmanrique.loldataproject.domain.entities.ChampionDetail
import com.jmanrique.loldataproject.domain.entities.ChampionSummary
import io.reactivex.rxjava3.core.Single

interface DataStore {
    fun getChampionSummary(): Single<List<ChampionSummary>>
    fun getChampionDetail(id: String): Single<ChampionDetail>
}