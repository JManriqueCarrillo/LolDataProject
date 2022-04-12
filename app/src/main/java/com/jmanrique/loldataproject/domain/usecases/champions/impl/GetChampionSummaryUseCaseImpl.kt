package com.jmanrique.loldataproject.domain.usecases.champions.impl

import com.jmanrique.loldataproject.domain.entities.ChampionSummary
import com.jmanrique.loldataproject.domain.repository.DragonRepository
import com.jmanrique.loldataproject.domain.usecases.champions.GetChampionSummaryUseCase
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class GetChampionSummaryUseCaseImpl @Inject constructor(
    private val dragonRepository: DragonRepository
): GetChampionSummaryUseCase {

    override fun execute(aVoid: Void?): Single<List<ChampionSummary>> {
        return dragonRepository.getChampionSummary()
    }
}