package com.mohamed.kedra.listingdemo.domain.usecase.details

import com.mohamed.kedra.listingdemo.domain.models.LaunchEntity
import com.mohamed.kedra.listingdemo.domain.repository.LaunchesRepository
import com.mohamed.kedra.listingdemo.domain.utils.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetLaunchDetailsUseCaseImpl @Inject constructor(
    private val repository: LaunchesRepository
) : GetLaunchDetailsUseCase {
    override fun invoke(id: String): Flow<Resource<LaunchEntity>> {
        return repository.getLaunchItemById(id)
    }
}