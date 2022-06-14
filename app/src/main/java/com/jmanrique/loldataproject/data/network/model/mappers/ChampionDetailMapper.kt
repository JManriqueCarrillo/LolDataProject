package com.jmanrique.loldataproject.data.network.model.mappers

import com.jmanrique.loldataproject.data.network.model.championDetail.WSChampionDetail
import com.jmanrique.loldataproject.domain.base.mapper.BaseSingleMapper
import com.jmanrique.loldataproject.domain.entities.ChampionDetail

class ChampionDetailMapper : BaseSingleMapper<WSChampionDetail, ChampionDetail>() {
    override fun transform(dataModel: WSChampionDetail) =
        ChampionDetail(
            alias = dataModel.alias,
            banVoPath = dataModel.banVoPath,
            chooseVoPath = dataModel.chooseVoPath,
            id = dataModel.id,
            name = dataModel.name,
            passive = dataModel.passive,
            playstyleInfo = dataModel.playstyleInfo,
            recommendedItemDefaults = dataModel.recommendedItemDefaults,
            roles = dataModel.roles,
            shortBio = dataModel.shortBio,
            skins = dataModel.skins,
            spells = dataModel.spells,
            squarePortraitPath = dataModel.squarePortraitPath,
            stingerSfxPath = dataModel.stingerSfxPath,
            tacticalInfo = dataModel.tacticalInfo,
            title = dataModel.title
        )
}
