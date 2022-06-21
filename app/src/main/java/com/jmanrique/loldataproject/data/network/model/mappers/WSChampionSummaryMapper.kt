package com.jmanrique.loldataproject.data.network.model.mappers

import com.jmanrique.loldataproject.data.network.model.championSummary.WSChampionSummary
import com.jmanrique.loldataproject.domain.base.mapper.BaseSingleMapper
import com.jmanrique.loldataproject.domain.entities.ChampionSummary

class WSChampionSummaryMapper : BaseSingleMapper<List<WSChampionSummary>, List<ChampionSummary>>() {
    override fun transform(dataModel: List<WSChampionSummary>) = dataModel.map {
        ChampionSummary(
            id = it.id,
            alias = it.alias,
            name = it.name,
            roles = it.roles,
            squarePortraitPath = it.squarePortraitPath
        )
    }
}