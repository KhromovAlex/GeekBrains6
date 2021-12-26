package com.example.gbapp6

import android.os.Build
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.gbapp6.data.dispatchers.DispatchersProviderTest
import com.example.gbapp6.domain.entity.AppState
import com.example.gbapp6.domain.entity.DataModel
import com.example.gbapp6.domain.repository.DictionaryRepository
import com.example.gbapp6.presentation.history.HistoryListViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.robolectric.annotation.Config

@RunWith(AndroidJUnit4::class)
@Config(sdk = [Build.VERSION_CODES.O_MR1])
@ExperimentalCoroutinesApi
class HistoryListViewModelTest {
    companion object {
        const val SEARCH_QUERY = "hello"
    }

    private lateinit var historyListViewModel: HistoryListViewModel

    @Mock
    private lateinit var repository: DictionaryRepository

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        historyListViewModel = HistoryListViewModel(repository, DispatchersProviderTest())
    }

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    @Test
    fun assertSearchDataSuccess() = testCoroutineRule.runBlockingTest {
        val observer = Observer<AppState<DataModel>> {}
        val successResult = DataModel(
            id = 0,
            text = "text",
            meanings = listOf()
        )

        Mockito.`when`(repository.getHistoryByText(SEARCH_QUERY))
            .thenReturn(successResult)

        try {
            historyListViewModel.liveData.observeForever(observer)
            historyListViewModel.searchData(SEARCH_QUERY)
            val value: AppState.Success<DataModel> =
                historyListViewModel.liveData.value as AppState.Success<DataModel>
            Assert.assertEquals(value.data, successResult)
        } finally {
            historyListViewModel.liveData.removeObserver(observer)
        }

    }

    @Test
    fun assertSearchDataError() = testCoroutineRule.runBlockingTest {
        val observer = Observer<AppState<DataModel>> {}
        val errorResult = RuntimeException()

        Mockito.`when`(repository.getHistoryByText(SEARCH_QUERY))
            .thenThrow(errorResult)

        try {
            historyListViewModel.liveData.observeForever(observer)
            historyListViewModel.searchData(SEARCH_QUERY)
            val value: AppState.Error<DataModel> =
                historyListViewModel.liveData.value as AppState.Error<DataModel>
            Assert.assertEquals(value.error, errorResult)
        } finally {
            historyListViewModel.liveData.removeObserver(observer)
        }

    }

    @Test
    fun assertClearAppStateTrue() = testCoroutineRule.runBlockingTest {
        val observer = Observer<AppState<DataModel>> {}

        try {
            historyListViewModel.liveData.observeForever(observer)
            historyListViewModel.clearAppState()
            val value = historyListViewModel.liveData.value
            Assert.assertTrue(value is AppState.Empty<DataModel>)
        } finally {
            historyListViewModel.liveData.removeObserver(observer)
        }

    }

}
