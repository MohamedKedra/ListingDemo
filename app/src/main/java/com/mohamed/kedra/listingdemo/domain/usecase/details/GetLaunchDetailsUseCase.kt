package com.mohamed.kedra.listingdemo.domain.usecase.details

import com.mohamed.kedra.listingdemo.domain.models.LaunchEntity
import com.mohamed.kedra.listingdemo.domain.utils.Resource
import kotlinx.coroutines.flow.Flow

interface GetLaunchDetailsUseCase {

    fun invoke(id : String) : Flow<Resource<LaunchEntity>>
}