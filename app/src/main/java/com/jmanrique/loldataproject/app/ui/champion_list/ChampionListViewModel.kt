package com.jmanrique.loldataproject.app.ui.champion_list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.jmanrique.loldataproject.app.ui.base.BaseViewModel
import com.jmanrique.loldataproject.domain.entities.ChampionDetail
import com.jmanrique.loldataproject.domain.entities.ChampionSummary
import com.jmanrique.loldataproject.domain.usecases.champions.GetChampionDetailUseCase
import com.jmanrique.loldataproject.domain.usecases.champions.GetChampionSummaryUseCase
import com.jmanrique.loldataproject.utils.Resource
import com.jmanrique.loldataproject.utils.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ChampionListViewModel @Inject constructor(
    private val getChampionSummaryUseCase: GetChampionSummaryUseCase,
    private val getChampionDetailUseCase: GetChampionDetailUseCase
) : BaseViewModel() {

    private val _championsList = MutableLiveData<Resource<MutableList<ChampionSummary>>>()
    val championsList: LiveData<Resource<MutableList<ChampionSummary>>> get() = _championsList

    private lateinit var _originalList: List<ChampionSummary>
    private var _currentQuery: String = ""
    fun currentQuery() = _currentQuery

    private val _championDetail = MutableLiveData<Resource<ChampionDetail?>>()
    val championDetail: LiveData<Resource<ChampionDetail?>> get() = _championDetail

    val showLoading = SingleLiveEvent<Boolean>().apply { value = false }
    val showEmpty = SingleLiveEvent<Boolean>().apply { value = false }

    fun getChampionSummary() {
        showLoading.value = true
        showEmpty.value = false
        subscribe(getChampionSummaryUseCase.execute(null),
            onSuccess = {
                _originalList = it
                    .filter { champion -> champion.id != -1 }
                    .sortedBy { champion -> champion.alias }
                    .toMutableList()

                _championsList.postValue(Resource.success(_originalList as MutableList<ChampionSummary>))
                showLoading.value = false
            },
            onError = {
                showEmpty.value = true
                showLoading.value = false
                _championsList.postValue(Resource.error("Something went wrong", mutableListOf()))
            })
    }

    fun getChampionDetail(championId: String) {
        showLoading.value = true
        subscribe(getChampionDetailUseCase.execute(championId),
            onSuccess = {
                _championDetail.postValue(Resource.success(it))
                showLoading.value = false
            }, onError = {
                _championDetail.postValue(Resource.error("Something went wrong", null))
                showLoading.value = false
            })
    }

    fun filterList(searchTerm: String) {
        _currentQuery = searchTerm
        if (_currentQuery.isNotEmpty()) {
            _championsList.postValue(
                Resource.success(
                    _originalList.filter {
                        it.name.uppercase().contains(_currentQuery.uppercase())
                    }.toMutableList()
                )
            )
        } else {
            if (_championsList.value?.data?.equals(_originalList) == false)
                _championsList.postValue(Resource.success(_originalList as MutableList<ChampionSummary>))
        }
    }

}