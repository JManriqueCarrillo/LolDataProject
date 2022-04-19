package com.jmanrique.loldataproject.app.repository

import com.jmanrique.loldataproject.data.repository.DragonLocalStoreImpl
import com.jmanrique.loldataproject.data.repository.DragonRemoteStoreImpl
import com.jmanrique.loldataproject.domain.entities.ChampionDetail
import com.jmanrique.loldataproject.domain.entities.ChampionSummary
import com.jmanrique.loldataproject.domain.repository.DragonRepository
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class DefaultRepository @Inject constructor(
    private val localStore: DragonLocalStoreImpl,
    private val remoteStore: DragonRemoteStoreImpl
) : DragonRepository {

    override fun getChampionSummary(): Single<List<ChampionSummary>> {
        //TODO Check if DB has data
        return remoteStore.getChampionSummary()
    }

    override fun getChampionDetail(championId: String): Single<ChampionDetail> {
        return remoteStore.getChampionDetail(championId)
    }


}