package com.jmanrique.loldataproject.domain.usecases.preferences.impl

import com.jmanrique.loldataproject.domain.repository.SharedPreferencesRepository
import com.jmanrique.loldataproject.domain.usecases.preferences.SetCurrentPatchToPreferences
import io.reactivex.rxjava3.core.Completable
import javax.inject.Inject

class SetCurrentPatchToPreferencesImpl @Inject constructor(
    private val prefRepository: SharedPreferencesRepository
) : SetCurrentPatchToPreferences {

    override fun execute(params: String): Completable = prefRepository.setCurrentPatch(params)

}