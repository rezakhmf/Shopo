package com.farahaniconsulting.shopo.data.repository

import com.farahaniconsulting.shopo.model.ShoppingItems
import io.reactivex.Single

interface ShopoRepository {
    fun getShoppingItems(): Single<ShoppingItems>
}