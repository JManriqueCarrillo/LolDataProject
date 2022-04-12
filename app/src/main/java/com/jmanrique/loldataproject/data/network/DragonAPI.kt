package com.jmanrique.loldataproject.data.network

import com.jmanrique.loldataproject.data.network.model.championSummary.WSChampionSummary
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface DragonAPI {

    @GET("/{patch}/plugins/rcp-be-lol-game-data/global/{locale}/v1/champion-summary.json")
    fun getChampionSummary(
        @Path("patch") patch: String? = APIConstants.patch,
        @Path("locale") locale: String? = APIConstants.locale
    ): Single<List<WSChampionSummary>>

}