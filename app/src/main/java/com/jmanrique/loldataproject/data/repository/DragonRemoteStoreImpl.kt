package com.jmanrique.loldataproject.data.repository

import com.jmanrique.loldataproject.data.network.DragonAPI
import com.jmanrique.loldataproject.data.network.model.mappers.WSChampionDetailMapper
import com.jmanrique.loldataproject.data.network.model.mappers.WSChampionSummaryMapper
import com.jmanrique.loldataproject.data.network.model.mappers.WSContentMetadataMapper
import com.jmanrique.loldataproject.domain.entities.ChampionDetail
import com.jmanrique.loldataproject.domain.entities.ChampionSummary
import com.jmanrique.loldataproject.domain.repository.DataStore
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class DragonRemoteStoreImpl @Inject constructor(
    private val dragonAPI: DragonAPI
) : DataStore {

    fun getContentMetadata(): Single<String> =
        dragonAPI.getContentMetadata().map(WSContentMetadataMapper().getTransformMapper())

    override fun getChampionSummary(): Single<List<ChampionSummary>> =
        dragonAPI.getChampionSummary().map(WSChampionSummaryMapper().getTransformMapper())

    override fun getChampionDetail(id: String): Single<ChampionDetail> =
        dragonAPI.getChampionDetail(id = id)
            .map(WSChampionDetailMapper().getTransformMapper())

}