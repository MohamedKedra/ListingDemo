package com.mohamed.kedra.listingdemo.presentation.features.details

import com.mohamed.kedra.listingdemo.domain.models.LaunchEntity

data class DetailsState(
    val launchEntity: LaunchEntity? = null,
    val isLoading : Boolean = false,
    val error: String? = null
)
