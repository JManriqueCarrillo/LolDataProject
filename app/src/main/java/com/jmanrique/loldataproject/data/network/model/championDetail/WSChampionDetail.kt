package com.jmanrique.loldataproject.data.network.model.championDetail

import com.jmanrique.loldataproject.domain.entities.ChampionDetail

data class WSChampionDetail(
    val alias: String,
    val banVoPath: String,
    val chooseVoPath: String,
    val id: Int,
    val name: String,
    val passive: Passive,
    val playstyleInfo: PlaystyleInfo,
    val recommendedItemDefaults: List<Any>,
    val roles: List<String>,
    val shortBio: String,
    val skins: List<Skin>,
    val spells: List<Spell>,
    val squarePortraitPath: String,
    val stingerSfxPath: String,
    val tacticalInfo: TacticalInfo,
    val title: String
) {

    fun toChampionDetail() = ChampionDetail(
        alias = this.alias,
        banVoPath = this.banVoPath,
        chooseVoPath = this.chooseVoPath,
        id = this.id,
        name = this.name,
        passive = this.passive,
        playstyleInfo = this.playstyleInfo,
        recommendedItemDefaults = this.recommendedItemDefaults,
        roles = this.roles,
        shortBio = this.shortBio,
        skins = this.skins,
        spells = this.spells,
        squarePortraitPath = this.squarePortraitPath,
        stingerSfxPath = this.stingerSfxPath,
        tacticalInfo = this.tacticalInfo,
        title = this.title
    )
}