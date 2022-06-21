package com.jmanrique.loldataproject.domain.usecases.metadata.impl

import com.jmanrique.loldataproject.domain.repository.DragonRepository
import com.jmanrique.loldataproject.domain.usecases.metadata.GetCurrentPatchUseCase
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class GetCurrentPatchUseCaseImpl @Inject constructor(
    private val dragonRepository: DragonRepository
) : GetCurrentPatchUseCase {
    override fun execute(params: Void?): Single<String> {
        return dragonRepository.getCurrentPatch()
    }
}