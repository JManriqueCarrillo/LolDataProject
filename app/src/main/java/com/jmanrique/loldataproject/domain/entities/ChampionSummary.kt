package com.jmanrique.loldataproject.domain.entities

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.jmanrique.loldataproject.data.local.model.DBConstants

@Entity(tableName = DBConstants.CHAMPION_SUMMARY_TABLE_NAME)
data class ChampionSummary(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = DBConstants.CHAMPION_SUMMARY_PRIMARY_KEY)
    val id: Int,
    val alias: String,
    val name: String,
    val roles: List<String>,
    val squarePortraitPath: String,
    @Ignore
    var showInfo: Boolean = false,
    @Ignore
    var isInfo: Boolean = false,
    var title: String = "",
    var shortBio: String = ""
) {

    //Constructor por Database
    constructor(
        id: Int,
        alias: String,
        name: String,
        roles: List<String>,
        squarePortraitPath: String
    ) : this(id, alias, name, roles, squarePortraitPath, false, false, "", "")

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