package com.jmanrique.loldataproject.domain.usecases.preferences.impl

import com.jmanrique.loldataproject.domain.repository.SharedPreferencesRepository
import com.jmanrique.loldataproject.domain.usecases.preferences.GetCurrentPatchFromPreferences
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class GetCurrentPatchFromPreferencesImpl @Inject constructor(
    private val prefRepository: SharedPreferencesRepository
) : GetCurrentPatchFromPreferences {

    override fun execute(params: Void?): Single<String> = prefRepository.getCurrentPatch()

}