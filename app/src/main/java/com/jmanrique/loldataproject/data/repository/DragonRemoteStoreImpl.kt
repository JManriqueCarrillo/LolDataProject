package com.jmanrique.loldataproject.data.repository

import com.jmanrique.loldataproject.app.repository.DataStore
import com.jmanrique.loldataproject.data.network.DragonAPI
import javax.inject.Inject

class DragonRemoteStoreImpl @Inject constructor(
    private val dragonAPI: DragonAPI
) : DataStore{
}