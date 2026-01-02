package com.mohamed.kedra.listingdemo.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.apollographql.apollo3.ApolloClient
import com.mohamed.kedra.listingdemo.LaunchQuery
import com.mohamed.kedra.listingdemo.data.mapper.toDomain
import com.mohamed.kedra.listingdemo.data.pagination.LaunchesPagingSource
import com.mohamed.kedra.listingdemo.domain.models.LaunchEntity
import com.mohamed.kedra.listingdemo.domain.repository.LaunchesRepository
import com.mohamed.kedra.listingdemo.domain.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class LaunchesRepositoryImpl @Inject constructor(
    private val apolloClient: ApolloClient
) : LaunchesRepository {

    override fun getAllLaunches(): Flow<PagingData<LaunchEntity>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                prefetchDistance = 5,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                LaunchesPagingSource(apolloClient)
            }
        ).flow
    }

    override fun getLaunchItemById(id: String): Flow<Resource<LaunchEntity>> = flow {
        try {
            emit(Resource.Loading)
            val  response = apolloClient.query(LaunchQuery(id)).execute()
            val launchData = response.dataAssertNoErrors.launch

            if (launchData != null) {
                emit(Resource.Success(launchData.toDomain()))
            }else{
                emit(
                    Resource.Error(
                        exception = Exception("No launch details to display."),
                        message = "No launch details to display."
                    )
                )
            }

        } catch (e: Exception) {
            e.printStackTrace()
            emit(
                Resource.Error(
                    exception = e,
                    message = e.message ?: "Failed to fetch launch details."
                )
            )
        }
    }.flowOn(Dispatchers.IO)
}