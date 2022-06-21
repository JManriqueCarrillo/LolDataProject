package com.jmanrique.loldataproject.domain.usecases.champions.impl

import com.jmanrique.loldataproject.domain.entities.ChampionSummary
import com.jmanrique.loldataproject.domain.repository.DragonRepository
import com.jmanrique.loldataproject.domain.usecases.champions.SaveChampionSummaryUseCase
import io.reactivex.rxjava3.core.Completable
import javax.inject.Inject

class SaveChampionSummaryUseCaseImpl @Inject constructor(
    private val dragonRepository: DragonRepository
) : SaveChampionSummaryUseCase {

    override fun execute(params: List<ChampionSummary>): Completable =
        dragonRepository.saveChampionSummary(params)

}