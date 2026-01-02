package com.mohamed.kedra.listingdemo

import com.mohamed.kedra.listingdemo.domain.models.LaunchEntity
import com.mohamed.kedra.listingdemo.domain.usecase.details.GetLaunchDetailsUseCase
import com.mohamed.kedra.listingdemo.domain.utils.Resource
import kotlinx.coroutines.flow.flow

class FakeGetLaunchDetailsUseCase : GetLaunchDetailsUseCase {

    private var result: Resource<LaunchEntity> = Resource.Loading

    fun emit(value: Resource<LaunchEntity>) {
        result = value
    }

    override operator fun invoke(id: String) = flow {
        emit(Resource.Loading)
        emit(result)
    }
}