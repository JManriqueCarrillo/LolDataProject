package com.jmanrique.loldataproject.data.repository

import com.jmanrique.loldataproject.domain.repository.DataStore
import com.jmanrique.loldataproject.data.network.DragonAPI
import com.jmanrique.loldataproject.domain.entities.ChampionSummary
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class DragonRemoteStoreImpl @Inject constructor(
    private val dragonAPI: DragonAPI
) : DataStore {
    override fun getChampionSummary(): Single<List<ChampionSummary>> =
        dragonAPI.getChampionSummary().map { list ->
            list.map { it.toChampionSummary() }
        }
}