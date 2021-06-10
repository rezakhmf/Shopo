package com.farahaniconsulting.shopo.data.network

import com.farahaniconsulting.shopo.model.ShoppingItems
import io.reactivex.Single
import retrofit2.http.GET

interface ApiService {
    @GET("")
    fun getShoppingItems(): Single<ShoppingItems>
}