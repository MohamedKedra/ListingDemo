package com.mohamed.kedra.listingdemo

import androidx.paging.PagingData
import com.mohamed.kedra.listingdemo.domain.models.LaunchEntity
import com.mohamed.kedra.listingdemo.domain.usecase.getAllLaunches.GetAllLaunchesUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class FakeGetAllLaunchesUseCase : GetAllLaunchesUseCase {
    override fun invoke(): Flow<PagingData<LaunchEntity>> {
        return flowOf(PagingData.empty())
    }
}