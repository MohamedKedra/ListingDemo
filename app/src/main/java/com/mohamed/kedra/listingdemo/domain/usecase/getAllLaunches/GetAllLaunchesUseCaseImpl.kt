package com.mohamed.kedra.listingdemo.domain.usecase.getAllLaunches

import androidx.paging.PagingData
import com.mohamed.kedra.listingdemo.domain.models.LaunchEntity
import com.mohamed.kedra.listingdemo.domain.repository.LaunchesRepository
import com.mohamed.kedra.listingdemo.domain.utils.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllLaunchesUseCaseImpl @Inject constructor(
    private val launchesRepository: LaunchesRepository
) : GetAllLaunchesUseCase {

    override fun invoke(): Flow<PagingData<LaunchEntity>> {
        return launchesRepository.getAllLaunches()
    }
}