package com.jmanrique.loldataproject.domain.entities

import com.jmanrique.loldataproject.utils.extensions.sameContentWith

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
) {
    override fun hashCode(): Int =
        if (isInfo) super.hashCode()
        else this.id.hashCode()

    override fun equals(other: Any?): Boolean {
        return if (other is ChampionSummary) {
            val itemCompare = other as ChampionSummary
            id == itemCompare.id && alias == itemCompare.alias && name == itemCompare.name

        } else false
    }

}