package com.jmanrique.loldataproject.data.repository

import com.jmanrique.loldataproject.app.repository.DataStore
import com.jmanrique.loldataproject.data.local.DragonDAO
import javax.inject.Inject

class DragonLocalStoreImpl @Inject constructor(
    private val dragonDAO: DragonDAO
) : DataStore{
}