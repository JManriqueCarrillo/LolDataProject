package com.jmanrique.loldataproject.app.repository

import com.jmanrique.loldataproject.data.repository.DragonLocalStoreImpl
import com.jmanrique.loldataproject.data.repository.DragonRemoteStoreImpl
import com.jmanrique.loldataproject.domain.entities.ChampionDetail
import com.jmanrique.loldataproject.domain.entities.ChampionSummary
import com.jmanrique.loldataproject.domain.repository.DragonRepository
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class DefaultRepository @Inject constructor(
    private val localStore: DragonLocalStoreImpl,
    private val remoteStore: DragonRemoteStoreImpl
) : DragonRepository {

    override fun getChampionSummary(): Single<List<ChampionSummary>> {
        return if (shouldFetch()) {
            remoteStore.getChampionSummary()
        } else {
            localStore.getChampionSummary()
        }
    }


    override fun getChampionDetail(championId: String): Single<ChampionDetail> {
        return remoteStore.getChampionDetail(championId)
    }

    override fun saveChampionSummary(data: List<ChampionSummary>): Completable =
        localStore.saveChampionSummary(data)

    private fun shouldFetch(): Boolean {
        var shouldFetch = true
        localStore.getChampionSummary().map {
            if (!it.isNullOrEmpty())
                shouldFetch = false
        }
        return shouldFetch
    }

}