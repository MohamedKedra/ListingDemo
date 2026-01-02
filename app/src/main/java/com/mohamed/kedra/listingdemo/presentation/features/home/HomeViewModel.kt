package com.mohamed.kedra.listingdemo.presentation.features.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import androidx.paging.cachedIn
import com.mohamed.kedra.listingdemo.domain.usecase.getAllLaunches.GetAllLaunchesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    getAllLaunchesUseCase: GetAllLaunchesUseCase
) : ViewModel() {

    val launches = getAllLaunchesUseCase.invoke()
        .cachedIn(viewModelScope)

    private val _state = MutableStateFlow(HomeState())
    val state: StateFlow<HomeState> = _state.asStateFlow()

    fun handlePagingLoadState(loadState: CombinedLoadStates) {
        _state.update { current ->
            current.copy(
                showEmptyState =
                    loadState.refresh is LoadState.NotLoading &&
                            loadState.append.endOfPaginationReached &&
                            loadState.source.refresh is LoadState.NotLoading,
                errorMessage = (loadState.refresh as? LoadState.Error)?.error?.message
            )
        }
    }
}
