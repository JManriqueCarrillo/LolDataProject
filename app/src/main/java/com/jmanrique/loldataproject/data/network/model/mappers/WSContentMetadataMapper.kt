package com.jmanrique.loldataproject.data.network.model.mappers

import com.jmanrique.loldataproject.data.network.model.metadata.WSContentMetadata
import com.jmanrique.loldataproject.domain.base.mapper.BaseSingleMapper

class WSContentMetadataMapper : BaseSingleMapper<WSContentMetadata, String>() {
    override fun transform(dataModel: WSContentMetadata): String {
        val split = dataModel.version.split('.')
        return "${split[0]}.${split[1]}" //Only major and minor version
    }
}