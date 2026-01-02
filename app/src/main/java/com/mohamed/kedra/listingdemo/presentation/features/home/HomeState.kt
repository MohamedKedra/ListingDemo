package com.mohamed.kedra.listingdemo.presentation.features.home

data class HomeState(
    val showEmptyState: Boolean = false,
    val errorMessage: String? = null
)