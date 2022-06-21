package com.jmanrique.loldataproject.data.repository

import com.jmanrique.loldataproject.data.local.preferences.SharedPreferencesManager
import com.jmanrique.loldataproject.domain.repository.SharedPreferencesRepository
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class SharedPreferencesRepositoryImpl @Inject constructor(
    private val prefManager: SharedPreferencesManager
) : SharedPreferencesRepository {

    override fun setCurrentPatch(patch: String): Completable =
        prefManager.setCurrentPatch(patch)

    override fun getCurrentPatch(): Single<String> =
        prefManager.getCurrentPatch()
}