package com.jmanrique.loldataproject.data.network

import com.jmanrique.loldataproject.data.network.model.championDetail.WSChampionDetail
import com.jmanrique.loldataproject.data.network.model.championSummary.WSChampionSummary
import com.jmanrique.loldataproject.data.network.model.metadata.WSContentMetadata
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface DragonAPI {

    @GET("/{patch}/content-metadata.json")
    fun getContentMetadata(
        @Path("patch") patch: String? = APIConstants.patch
    ): Single<WSContentMetadata>

    @GET("/{patch}/plugins/rcp-be-lol-game-data/global/{locale}/v1/champion-summary.json")
    fun getChampionSummary(
        @Path("patch") patch: String? = APIConstants.patch,
        @Path("locale") locale: String? = APIConstants.locale
    ): Single<List<WSChampionSummary>>

    @GET("/{patch}/plugins/rcp-be-lol-game-data/global/{locale}/v1/champions/{id}.json")
    fun getChampionDetail(
        @Path("patch") patch: String? = APIConstants.patch,
        @Path("locale") locale: String? = APIConstants.locale,
        @Path("id") id: String
    ): Single<WSChampionDetail>

}