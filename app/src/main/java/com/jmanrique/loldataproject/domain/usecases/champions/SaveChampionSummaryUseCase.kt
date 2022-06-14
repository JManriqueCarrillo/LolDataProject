package com.jmanrique.loldataproject.domain.usecases.champions

import com.jmanrique.loldataproject.domain.entities.ChampionSummary
import com.jmanrique.loldataproject.domain.usecases.CompletableUseCase

interface SaveChampionSummaryUseCase: CompletableUseCase<List<ChampionSummary>> {
}