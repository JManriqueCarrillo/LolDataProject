package com.jmanrique.loldataproject.domain.usecases.champions

import com.jmanrique.loldataproject.domain.entities.ChampionSummary
import com.jmanrique.loldataproject.domain.base.usecase.SingleUseCase

interface GetChampionSummaryUseCase: SingleUseCase<Void?, List<ChampionSummary>> {
}