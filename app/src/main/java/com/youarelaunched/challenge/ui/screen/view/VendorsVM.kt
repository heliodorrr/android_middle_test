package com.youarelaunched.challenge.ui.screen.view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.youarelaunched.challenge.data.repository.VendorsRepository
import com.youarelaunched.challenge.data.repository.model.Vendor
import com.youarelaunched.challenge.ui.screen.state.VendorsScreenUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject
import kotlin.time.Duration.Companion.milliseconds
import kotlin.time.Duration.Companion.seconds

@HiltViewModel
@OptIn(FlowPreview::class)
class VendorsVM @Inject constructor(
    private val repository: VendorsRepository
) : ViewModel() {

    companion object {
        private val DEBOUNCE_TIME = 500.milliseconds
        private const val QUERY_SIZE_THRESHOLD = 3
    }

    private val queryActionFlow = MutableStateFlow<SearchQueryAction>(
        SearchQueryAction.Forced("")
    )

    @OptIn(ExperimentalCoroutinesApi::class)
    val uiState = queryActionFlow
        .debounce { action->
            when(action) {
                is SearchQueryAction.Auto -> DEBOUNCE_TIME
                is SearchQueryAction.Forced -> 0.seconds
            }
        }
        .filter { it is SearchQueryAction.Forced || it.query.length >= QUERY_SIZE_THRESHOLD }
        .distinctUntilChanged()
        .mapLatest { action->
            VendorsScreenUiState(
                vendors = getVendors(action.query)
            )
        }.stateIn(viewModelScope, SharingStarted.Eagerly, VendorsScreenUiState.INITIAL)

    private suspend fun getVendors(query: String): List<Vendor>? {
        return runCatching {
            if(query.isEmpty()) {
                repository.getAllVendors()
            } else {
                repository.getVendors(query)
            }
        }.getOrNull()
    }

    fun updateQuery(action: SearchQueryAction) { queryActionFlow.value = action }

}