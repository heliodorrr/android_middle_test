package com.youarelaunched.challenge.ui.screen.view.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.youarelaunched.challenge.middle.R
import com.youarelaunched.challenge.ui.theme.VendorAppTheme
import com.youarelaunched.challenge.ui.theme.VendorAppTypography

@Composable
fun NoItemsPresent(
    modifier: Modifier = Modifier
) {

    Column(
        modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Spacer(modifier = Modifier.height(220.dp))


        Text(
            text = stringResource(R.string.no_result_found_header),
            style = VendorAppTheme.typography.h2,
            color = VendorAppTheme.colors.textSpecial,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )

        Text(
            text = stringResource(R.string.no_result_found_suggestion),
            style = VendorAppTheme.typography.subtitle2,
            color = VendorAppTheme.colors.textDark,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp)
        )


    }



}