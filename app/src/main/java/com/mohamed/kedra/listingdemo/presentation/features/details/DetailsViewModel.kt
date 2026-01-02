package com.mohamed.kedra.listingdemo.presentation.features.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mohamed.kedra.listingdemo.domain.usecase.details.GetLaunchDetailsUseCase
import com.mohamed.kedra.listingdemo.domain.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val getLaunchDetailsUseCase: GetLaunchDetailsUseCase
) : ViewModel(){

    private val _state = MutableStateFlow(DetailsState())
    val state: StateFlow<DetailsState> = _state.asStateFlow()

    fun setEvent(event: DetailsEvent) {
        when (event) {
            is DetailsEvent.GetAllDetails -> getLaunchDetails(event.id)
        }
    }

    private fun getLaunchDetails(id: String) {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true, error = null) }

            getLaunchDetailsUseCase.invoke(id).collect { result ->
                when (result) {
                    is Resource.Loading -> _state.update { it.copy(isLoading = true) }
                    is Resource.Success -> _state.update {
                        it.copy(
                            launchEntity = result.data,
                            isLoading = false
                        )
                    }

                    is Resource.Error -> _state.update {
                        it.copy(error = result.message)
                    }
                }
            }
        }
    }
}