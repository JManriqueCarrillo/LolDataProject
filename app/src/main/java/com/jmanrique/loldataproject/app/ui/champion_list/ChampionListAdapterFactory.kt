package com.jmanrique.loldataproject.app.ui.champion_list

import dagger.assisted.AssistedFactory

@AssistedFactory
interface ChampionListAdapterFactory {
    fun create(nColumns: Int): ChampionListAdapter
}