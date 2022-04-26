package com.jmanrique.loldataproject.domain.usecases.champions.impl

import com.jmanrique.loldataproject.domain.entities.ChampionDetail
import com.jmanrique.loldataproject.domain.repository.DragonRepository
import com.jmanrique.loldataproject.domain.usecases.champions.GetChampionDetailUseCase
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class GetChampionDetailUseCaseImpl @Inject constructor(
    private val dragonRepository: DragonRepository
) : GetChampionDetailUseCase {
    override fun execute(params: String): Single<ChampionDetail> {
        return dragonRepository.getChampionDetail(params)
    }
}