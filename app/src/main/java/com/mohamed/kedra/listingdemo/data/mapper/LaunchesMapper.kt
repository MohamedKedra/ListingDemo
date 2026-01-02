package com.mohamed.kedra.listingdemo.data.mapper

import com.mohamed.kedra.listingdemo.LaunchQuery
import com.mohamed.kedra.listingdemo.LaunchesQuery
import com.mohamed.kedra.listingdemo.domain.models.LaunchEntity

fun LaunchQuery.Launch.toDomain(): LaunchEntity {
    return LaunchEntity(
        id = id,
        site = site ?: "",
        missionName = mission?.name ?: "",
        missionPatch = mission?.missionPatch ?: "",
        rocketId = rocket?.id ?: "",
        rocketName = rocket?.name ?: "",
        rocketType = rocket?.type ?: ""
    )
}

fun LaunchesQuery.Launch.toDomainListing(): LaunchEntity {
    return LaunchEntity(
        id = id,
        site = site ?: "",
        missionName = mission?.name ?: "",
        missionPatch = mission?.missionPatch ?: "",
        rocketId = rocket?.id ?: "",
        rocketName = rocket?.name ?: "",
        rocketType = rocket?.type ?: ""
    )
}