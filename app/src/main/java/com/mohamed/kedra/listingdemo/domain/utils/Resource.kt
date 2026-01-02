package com.mohamed.kedra.listingdemo.domain.utils

sealed class Resource<out T> {
    object Loading : Resource<Nothing>()
    data class Success<out T>(val data: T) : Resource<T>()
    data class Error(val exception: Exception, val message: String? = null) : Resource<Nothing>()
}