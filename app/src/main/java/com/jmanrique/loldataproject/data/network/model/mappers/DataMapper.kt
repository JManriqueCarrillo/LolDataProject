package com.jmanrique.loldataproject.data.network.model.mappers

import com.jmanrique.loldataproject.data.network.model.championDetail.WSChampionDetail
import com.jmanrique.loldataproject.data.network.model.championSummary.WSChampionSummary
import com.jmanrique.loldataproject.domain.entities.ChampionDetail
import com.jmanrique.loldataproject.domain.entities.ChampionSummary
import javax.inject.Inject

class DataMapper @Inject constructor() {

    fun mapChampionSummary(wsChampion: WSChampionSummary) = ChampionSummary(
        id = wsChampion.id,
        alias = wsChampion.alias,
        name = wsChampion.name,
        roles = wsChampion.roles,
        squarePortraitPath = wsChampion.squarePortraitPath
    )

    fun mapChampionDetail(wsChampion: WSChampionDetail) = ChampionDetail(
        alias = wsChampion.alias,
        banVoPath = wsChampion.banVoPath,
        chooseVoPath = wsChampion.chooseVoPath,
        id = wsChampion.id,
        name = wsChampion.name,
        passive = wsChampion.passive,
        playstyleInfo = wsChampion.playstyleInfo,
        recommendedItemDefaults = wsChampion.recommendedItemDefaults,
        roles = wsChampion.roles,
        shortBio = wsChampion.shortBio,
        skins = wsChampion.skins,
        spells = wsChampion.spells,
        squarePortraitPath = wsChampion.squarePortraitPath,
        stingerSfxPath = wsChampion.stingerSfxPath,
        tacticalInfo = wsChampion.tacticalInfo,
        title = wsChampion.title
    )

}