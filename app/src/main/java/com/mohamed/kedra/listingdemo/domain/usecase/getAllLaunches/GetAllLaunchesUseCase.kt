package com.mohamed.kedra.listingdemo.domain.usecase.getAllLaunches

import androidx.paging.PagingData
import com.mohamed.kedra.listingdemo.domain.models.LaunchEntity
import kotlinx.coroutines.flow.Flow

interface GetAllLaunchesUseCase {

    fun invoke() : Flow<PagingData<LaunchEntity>>
}