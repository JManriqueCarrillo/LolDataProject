package com.jmanrique.loldataproject.data.repository

import com.jmanrique.loldataproject.data.network.DragonAPI
import com.jmanrique.loldataproject.data.network.model.mappers.DataMapper
import com.jmanrique.loldataproject.domain.entities.ChampionDetail
import com.jmanrique.loldataproject.domain.entities.ChampionSummary
import com.jmanrique.loldataproject.domain.repository.DataStore
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class DragonRemoteStoreImpl @Inject constructor(
    private val dragonAPI: DragonAPI,
    private val mapper: DataMapper
) : DataStore {
    override fun getChampionSummary(): Single<List<ChampionSummary>> =
        dragonAPI.getChampionSummary().map { list ->
            list.map { mapper.mapChampionSummary(it) }
        }

    override fun getChampionDetail(championId: String): Single<ChampionDetail> =
        dragonAPI.getChampionDetail(id = championId).map { mapper.mapChampionDetail(it) }

}