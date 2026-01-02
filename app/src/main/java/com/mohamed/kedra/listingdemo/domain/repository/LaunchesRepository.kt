package com.mohamed.kedra.listingdemo.domain.repository

import androidx.paging.PagingData
import com.mohamed.kedra.listingdemo.domain.models.LaunchEntity
import com.mohamed.kedra.listingdemo.domain.utils.Resource
import kotlinx.coroutines.flow.Flow

interface LaunchesRepository {

    fun getAllLaunches() : Flow<PagingData<LaunchEntity>>
    fun getLaunchItemById(id: String) : Flow<Resource<LaunchEntity>>
}