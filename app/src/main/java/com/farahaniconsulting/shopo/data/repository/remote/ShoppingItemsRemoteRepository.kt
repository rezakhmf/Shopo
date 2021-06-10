package com.farahaniconsulting.shopo.data.repository.remote

import com.farahaniconsulting.shopo.data.network.ApiService
import com.farahaniconsulting.shopo.data.repository.ShopoRepository
import com.farahaniconsulting.shopo.model.ShoppingItems
import io.reactivex.Single

class ShoppingItemsRemoteRepository(private val apiService: ApiService) : ShopoRepository {

    override fun getShoppingItems() =
        apiService.getShoppingItems()
}