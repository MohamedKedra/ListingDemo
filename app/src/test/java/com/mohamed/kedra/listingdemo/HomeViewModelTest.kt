import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import androidx.paging.LoadStates
import com.mohamed.kedra.listingdemo.FakeGetAllLaunchesUseCase
import com.mohamed.kedra.listingdemo.MainDispatcherRule
import com.mohamed.kedra.listingdemo.presentation.features.home.HomeViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class HomeViewModelTest {

    @get:Rule
    val dispatcherRule = MainDispatcherRule()

    private lateinit var viewModel: HomeViewModel

    @Before
    fun setup() {
        viewModel = HomeViewModel(
            getAllLaunchesUseCase = FakeGetAllLaunchesUseCase()
        )
    }

    @Test
    fun `show empty state when refresh is NotLoading and endOfPaginationReached`() =
        runTest {

            val loadStates = CombinedLoadStates(
                refresh = LoadState.NotLoading(endOfPaginationReached = false),
                prepend = LoadState.NotLoading(endOfPaginationReached = true),
                append = LoadState.NotLoading(endOfPaginationReached = true),
                source = LoadStates(
                    refresh = LoadState.NotLoading(false),
                    prepend = LoadState.NotLoading(true),
                    append = LoadState.NotLoading(true)
                ),
                mediator = null
            )

            viewModel.handlePagingLoadState(loadStates)

            val state = viewModel.state.value

            assert(state.showEmptyState)
            assert(state.errorMessage == null)
        }

    @Test
    fun `set error message when refresh is Error`() = runTest {

        val exception = RuntimeException("Network error")

        val loadStates = CombinedLoadStates(
            refresh = LoadState.Error(exception),
            prepend = LoadState.NotLoading(true),
            append = LoadState.NotLoading(true),
            source = LoadStates(
                refresh = LoadState.Error(exception),
                prepend = LoadState.NotLoading(true),
                append = LoadState.NotLoading(true)
            ),
            mediator = null
        )

        viewModel.handlePagingLoadState(loadStates)

        val state = viewModel.state.value

        assert(state.showEmptyState.not())
        assert(state.errorMessage == "Network error")
    }
}