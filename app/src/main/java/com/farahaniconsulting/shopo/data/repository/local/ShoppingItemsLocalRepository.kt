package com.farahaniconsulting.shopo.data.repository.local

import com.farahaniconsulting.shopo.data.repository.ShopoRepository
import com.farahaniconsulting.shopo.data.repository.extension.readFileFromResources

import com.farahaniconsulting.shopo.model.ShoppingItem
import com.farahaniconsulting.shopo.model.ShoppingItems
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import io.reactivex.Single
import java.io.InputStream

class ShoppingItemsLocalRepository() : ShopoRepository {


    override fun getShoppingItems(): Single<ShoppingItems> {

        var shoppingItemsType = object : TypeToken<ShoppingItems>() {}.type

        val result: ShoppingItems = Gson().fromJson("file name".readFileFromResources(), shoppingItemsType)

        return Single.just(result)
    }
}
