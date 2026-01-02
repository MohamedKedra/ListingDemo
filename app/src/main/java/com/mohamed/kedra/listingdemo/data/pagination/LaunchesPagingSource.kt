package com.mohamed.kedra.listingdemo.data.pagination

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.api.Optional
import com.mohamed.kedra.listingdemo.LaunchesQuery
import com.mohamed.kedra.listingdemo.data.mapper.toDomainListing
import com.mohamed.kedra.listingdemo.domain.models.LaunchEntity
import javax.inject.Inject

class LaunchesPagingSource @Inject constructor(
    private val apolloClient: ApolloClient
) : PagingSource<String, LaunchEntity>() {

    override suspend fun load(params: LoadParams<String>): LoadResult<String, LaunchEntity> {
        return try {
            val cursorOptional = params.key?.let {
                Optional.Present(it)
            } ?: Optional.Absent
            val response = apolloClient.query(LaunchesQuery(cursorOptional)).execute()
            val launchData = response.dataAssertNoErrors.launches

            val launches = launchData.launches
                .mapNotNull { it?.toDomainListing()}

            LoadResult.Page(
                data = launches,
                prevKey = null,
                nextKey = if (launchData.hasMore) launchData.cursor else null
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<String, LaunchEntity>): String? {
        return null
    }
}