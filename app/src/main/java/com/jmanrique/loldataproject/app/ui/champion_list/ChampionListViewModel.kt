package com.jmanrique.loldataproject.app.ui.champion_list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.jmanrique.loldataproject.app.ui.base.BaseViewModel
import com.jmanrique.loldataproject.domain.entities.ChampionSummary
import com.jmanrique.loldataproject.domain.usecases.champions.GetChampionSummaryUseCase
import com.jmanrique.loldataproject.utils.Resource
import com.jmanrique.loldataproject.utils.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ChampionListViewModel @Inject constructor(
    private val getChampionSummaryUseCase: GetChampionSummaryUseCase
) : BaseViewModel() {

    private val _championsList = MutableLiveData<Resource<List<ChampionSummary>>>()
    val championsList: LiveData<Resource<List<ChampionSummary>>> get() = _championsList

    val showLoading = SingleLiveEvent<Boolean>().apply { value = false }
    val showEmpty = SingleLiveEvent<Boolean>().apply { value = false }

    fun getChampionSummary() {
        showLoading.value = true
        showEmpty.value = false
        subscribe(getChampionSummaryUseCase.execute(null),
            onSuccess = {
                _championsList.postValue(Resource.success(it
                    .filter { champion -> champion.id != -1 }
                    .sortedBy { champion -> champion.alias }
                ))
                showLoading.value = false
            },
            onError = {
                showEmpty.value = true
                showLoading.value = false
                _championsList.postValue(Resource.error("Something went wrong", emptyList()))
            })
    }

}