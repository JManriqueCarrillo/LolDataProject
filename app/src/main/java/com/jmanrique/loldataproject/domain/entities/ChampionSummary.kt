package com.jmanrique.loldataproject.domain.entities

import java.io.Serializable

data class ChampionSummary(
    val id: Int,
    val alias: String,
    val name: String,
    val roles: List<String>,
    val squarePortraitPath: String,
    var showInfo: Boolean = false,
    var isInfo: Boolean = false,
    var title: String = "",
    var shortBio: String = ""
) : Serializable {
    override fun hashCode(): Int =
        if (isInfo) super.hashCode()
        else this.id.hashCode()


}