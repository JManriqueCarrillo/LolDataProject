package com.jmanrique.loldataproject.data.network.model.metadata

import com.google.gson.annotations.SerializedName

data class WSContentMetadata(
    @SerializedName("version")
    val version: String
)
