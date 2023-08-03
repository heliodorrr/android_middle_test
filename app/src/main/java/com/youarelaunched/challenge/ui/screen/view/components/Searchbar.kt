package com.youarelaunched.challenge.ui.screen.view.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.youarelaunched.challenge.middle.R
import com.youarelaunched.challenge.ui.screen.view.SearchQueryAction
import com.youarelaunched.challenge.ui.theme.VendorAppTheme
import com.youarelaunched.challenge.ui.theme.VendorAppTypography

@Composable
fun SearchBar(
    onUserInput: (SearchQueryAction)->Unit,
    modifier: Modifier,
) {

    var searchQuery by remember { mutableStateOf("") }

    val textStyle = VendorAppTypography.subtitle2
    Card(
        backgroundColor = VendorAppTheme.colors.background,
        contentColor = Color.Unspecified,
        elevation = 4.dp,
        modifier = modifier
            .fillMaxWidth()
            .height(40.dp)
    ) {

        Box(Modifier.padding(horizontal = 16.dp)) {
            PlaceholderTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.Center),
                value = searchQuery,
                placeholderValue =  stringResource(R.string.searchbar_hint),
                onValueChange = {
                    searchQuery = it
                                },
                textStyle = textStyle,
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
                keyboardActions = KeyboardActions(
                    onSearch = { onUserInput(SearchQueryAction.Forced(searchQuery)) }
                )
            )
            Icon(
                painter = painterResource(id = R.drawable.ic_search),
                contentDescription = null,
                modifier = Modifier
                    .size(24.dp)
                    .align(Alignment.CenterEnd)
                    .clickable { onUserInput(SearchQueryAction.Auto(searchQuery)) }
                ,
                tint = Color.Unspecified
            )
        }

    }
}

