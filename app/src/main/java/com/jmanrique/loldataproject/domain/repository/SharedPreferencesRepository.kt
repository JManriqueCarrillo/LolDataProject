package com.jmanrique.loldataproject.domain.repository

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

interface SharedPreferencesRepository {
    fun setCurrentPatch(patch: String): Completable
    fun getCurrentPatch(): Single<String>
}