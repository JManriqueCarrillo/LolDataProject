package com.jmanrique.loldataproject.data.repository

import com.jmanrique.loldataproject.domain.repository.DataStore
import com.jmanrique.loldataproject.data.local.DragonDAO
import com.jmanrique.loldataproject.domain.entities.ChampionSummary
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class DragonLocalStoreImpl @Inject constructor(
    private val dragonDAO: DragonDAO
) : DataStore {
    override fun getChampionSummary(): Single<List<ChampionSummary>> {
        TODO("Not yet implemented")
    }
}