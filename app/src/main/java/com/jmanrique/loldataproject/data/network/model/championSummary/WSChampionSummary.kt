package com.jmanrique.loldataproject.data.network.model.championSummary

import com.google.gson.annotations.SerializedName
import com.jmanrique.loldataproject.domain.entities.ChampionSummary

data class WSChampionSummary(
    @SerializedName("id")
    val id: Int,
    @SerializedName("alias")
    val alias: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("roles")
    val roles: List<String>,
    @SerializedName("squarePortraitPath")
    val squarePortraitPath: String
) {
    fun toChampionSummary() = ChampionSummary(
        id = this.id,
        alias = this.alias,
        name = this.name,
        roles = this.roles,
        squarePortraitPath = this.squarePortraitPath
    )
}