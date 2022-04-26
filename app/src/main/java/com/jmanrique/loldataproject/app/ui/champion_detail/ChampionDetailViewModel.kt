package com.jmanrique.loldataproject.app.ui.champion_detail

import com.jmanrique.loldataproject.app.ui.base.BaseViewModel
import com.jmanrique.loldataproject.domain.usecases.champions.GetChampionDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ChampionDetailViewModel @Inject constructor(
    private val getChampionDetailUseCase: GetChampionDetailUseCase
): BaseViewModel() {
}