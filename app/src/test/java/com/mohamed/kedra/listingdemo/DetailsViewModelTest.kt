package com.mohamed.kedra.listingdemo

import app.cash.turbine.test
import com.mohamed.kedra.listingdemo.domain.models.LaunchEntity
import com.mohamed.kedra.listingdemo.domain.utils.Resource
import com.mohamed.kedra.listingdemo.presentation.features.details.DetailsEvent
import com.mohamed.kedra.listingdemo.presentation.features.details.DetailsViewModel
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertFalse
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class DetailsViewModelTest {

    @get:Rule
    val dispatcherRule = MainDispatcherRule()

    private lateinit var fakeUseCase: FakeGetLaunchDetailsUseCase
    private lateinit var viewModel: DetailsViewModel

    @Before
    fun setup() {
        fakeUseCase = FakeGetLaunchDetailsUseCase()
        viewModel = DetailsViewModel(fakeUseCase)
    }

    @Test
    fun `when get details success state contains data and loading false`() = runTest {
        val launch = fakeLaunchEntity()
        fakeUseCase.emit(Resource.Success(launch))

        viewModel.state.test {
            viewModel.setEvent(DetailsEvent.GetAllDetails("1"))

            val loading = awaitItem()
            assertTrue(loading.isLoading)

            val success = awaitItem()
            assertEquals(launch, success.launchEntity)
            assertFalse(success.isLoading)
        }
    }

    @Test
    fun `when get details error state contains error message`() = runTest {
        fakeUseCase.emit(Resource.Error(Exception("Network error"),"Network error"))

        viewModel.state.test {
            viewModel.setEvent(DetailsEvent.GetAllDetails("1"))

            awaitItem() // loading
            val error = awaitItem()

            assertEquals("Network error", error.error)
            assertFalse(error.isLoading)
        }
    }
}

fun fakeLaunchEntity(
    id: String = "1",
    site: String = "KSC LC 39A",
    missionName: String = "CRS-21",
    missionPatch: String = "https://image.com/patch.png",
    rocketName: String = "Falcon 9",
    rocketType: String = "FT",
    rocketId: String = "Falcon9"
): LaunchEntity {
    return LaunchEntity(
        id = id,
        site = site,
        missionName = missionName,
        missionPatch = missionPatch,
        rocketName = rocketName,
        rocketId = rocketId,
        rocketType = rocketType
    )
}