package com.jmanrique.loldataproject.data.local.preferences

import android.content.Context
import androidx.core.content.edit
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class SharedPreferencesManagerImpl @Inject constructor(context: Context) :
    SharedPreferencesManager {

    companion object {
        const val DRAGON_PREFERENCES = "dragon_preferences"
        const val CURRENT_PATCH_KEY = "current_patch"
    }

    private val manager = context.getSharedPreferences(DRAGON_PREFERENCES, Context.MODE_PRIVATE)


    override fun setCurrentPatch(patch: String): Completable {
        return Completable.defer {
            manager.edit {
                putString(
                    CURRENT_PATCH_KEY,
                    patch
                )
            }
            Completable.complete()
        }
    }

    override fun getCurrentPatch(): Single<String> {
        return Single.defer {
            Single.just(manager.getString(CURRENT_PATCH_KEY, ""))
        }
    }
}