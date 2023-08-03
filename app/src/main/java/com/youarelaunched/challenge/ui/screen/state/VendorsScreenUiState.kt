package com.youarelaunched.challenge.ui.screen.state

import com.youarelaunched.challenge.data.repository.model.Vendor

data class VendorsScreenUiState(
    val vendors: List<Vendor>?
) {
    companion object {
        val INITIAL = VendorsScreenUiState(
            vendors = null
        )
    }
}
