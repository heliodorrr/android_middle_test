package com.youarelaunched.challenge.ui.screen.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.youarelaunched.challenge.ui.screen.state.VendorsScreenUiState
import com.youarelaunched.challenge.ui.screen.view.components.ChatsumerSnackbar
import com.youarelaunched.challenge.ui.screen.view.components.NoItemsPresent
import com.youarelaunched.challenge.ui.screen.view.components.SearchBar
import com.youarelaunched.challenge.ui.screen.view.components.VendorItem
import com.youarelaunched.challenge.ui.theme.VendorAppTheme

@Composable
fun VendorsRoute(
    viewModel: VendorsVM
) {
    val uiState by viewModel.uiState.collectAsState()

    VendorsScreen(
        uiState = uiState,
        onSearchQueryChanged = viewModel::updateQuery
    )
}

private val SidePaddings = 16.dp

@Composable
fun VendorsScreen(
    uiState: VendorsScreenUiState,
    onSearchQueryChanged: (SearchQueryAction)->Unit
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        backgroundColor = VendorAppTheme.colors.background,
        topBar = {
            SearchBar(
                onUserInput = onSearchQueryChanged,
                modifier = Modifier.padding(
                    start = SidePaddings,
                    end = SidePaddings,
                    top = 24.dp
                )
            )
        },
        snackbarHost = { ChatsumerSnackbar(it) }
    ) { paddings ->
        if (!uiState.vendors.isNullOrEmpty()) {
            LazyColumn(
                modifier = Modifier
                    .padding(paddings)
                    .fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                contentPadding = PaddingValues(
                    vertical = 24.dp,
                    horizontal = SidePaddings
                )
            ) {
                items(uiState.vendors) { vendor ->
                    VendorItem(
                        vendor = vendor
                    )
                }

            }

        } else {
            NoItemsPresent()
        }

    }
}