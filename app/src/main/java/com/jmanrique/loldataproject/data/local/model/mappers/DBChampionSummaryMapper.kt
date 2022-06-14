package com.jmanrique.loldataproject.data.local.model.mappers

import com.jmanrique.loldataproject.data.local.model.DBChampionSummary
import com.jmanrique.loldataproject.domain.base.mapper.BaseSingleMapper
import com.jmanrique.loldataproject.domain.entities.ChampionSummary

class DBChampionSummaryMapper :
    BaseSingleMapper<List<DBChampionSummary>, List<ChampionSummary>>() {
    override fun transform(dataModel: List<DBChampionSummary>) = dataModel.map {
        ChampionSummary(
            id = it.id,
            alias = it.alias,
            name = it.name,
            roles = it.roles,
            squarePortraitPath = it.squarePortraitPath
        )
    }
}