package com.mohamed.kedra.listingdemo.presentation.features.details

sealed interface DetailsEvent {

    data class GetAllDetails(val id : String) : DetailsEvent
}