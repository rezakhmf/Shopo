package com.farahaniconsulting.shopo.ui.shoppingitems

import com.farahaniconsulting.shopo.dto.ShoppingItemDTO
import com.farahaniconsulting.shopo.ui.data.PageErrorState

interface ShoppingItemsContract {

    data class ViewState(
        val isLoading: Boolean = false,
        val activityData: List<ShoppingItemDTO> = listOf(),
        val errorState: PageErrorState? = null
    )
}