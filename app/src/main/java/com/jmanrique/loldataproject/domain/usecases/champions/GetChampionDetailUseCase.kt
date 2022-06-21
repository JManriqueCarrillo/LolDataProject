package com.jmanrique.loldataproject.domain.usecases.champions

import com.jmanrique.loldataproject.domain.entities.ChampionDetail
import com.jmanrique.loldataproject.domain.usecases.SingleUseCase

interface GetChampionDetailUseCase: SingleUseCase<String, ChampionDetail> {
}