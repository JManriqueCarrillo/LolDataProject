package com.jmanrique.loldataproject.app.repository

import com.jmanrique.loldataproject.data.repository.DragonLocalStoreImpl
import com.jmanrique.loldataproject.data.repository.DragonRemoteStoreImpl
import com.jmanrique.loldataproject.domain.repository.MainRepository
import javax.inject.Inject

class DefaultRepository @Inject constructor(
    private val localStore: DragonLocalStoreImpl,
    private val remoteStore: DragonRemoteStoreImpl
) : MainRepository {
}