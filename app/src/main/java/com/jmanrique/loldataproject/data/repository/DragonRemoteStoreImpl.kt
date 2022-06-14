package com.jmanrique.loldataproject.data.repository

import com.jmanrique.loldataproject.data.network.DragonAPI
import com.jmanrique.loldataproject.data.network.model.mappers.ChampionDetailMapper
import com.jmanrique.loldataproject.data.network.model.mappers.ChampionSummaryMapper
import com.jmanrique.loldataproject.domain.entities.ChampionDetail
import com.jmanrique.loldataproject.domain.entities.ChampionSummary
import com.jmanrique.loldataproject.domain.repository.DataStore
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class DragonRemoteStoreImpl @Inject constructor(
    private val dragonAPI: DragonAPI
) : DataStore {
    override fun getChampionSummary(): Single<List<ChampionSummary>> =
        dragonAPI.getChampionSummary().map(ChampionSummaryMapper().getTransformMapper())

    override fun getChampionDetail(championId: String): Single<ChampionDetail> =
        dragonAPI.getChampionDetail(id = championId)
            .map(ChampionDetailMapper().getTransformMapper())

}