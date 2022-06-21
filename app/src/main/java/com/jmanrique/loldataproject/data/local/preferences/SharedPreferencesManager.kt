package com.jmanrique.loldataproject.data.local.preferences

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

interface SharedPreferencesManager {

    fun setCurrentPatch(patch: String): Completable
    fun getCurrentPatch(): Single<String>

}