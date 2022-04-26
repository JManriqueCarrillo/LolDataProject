package com.jmanrique.loldataproject.domain.entities

import com.jmanrique.loldataproject.data.network.model.championDetail.*

data class ChampionDetail(
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
)