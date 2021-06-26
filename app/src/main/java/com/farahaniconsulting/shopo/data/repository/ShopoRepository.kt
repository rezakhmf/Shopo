package com.farahaniconsulting.shopo.data.repository

import com.farahaniconsulting.shopo.model.ShoppingItems
import io.reactivex.Single

const val REPOSITORY_LOCAL = "repositoryLocal"
const val REPOSITORY_REMOTE = "repositoryRemote"

interface ShopoRepository {
    fun getShoppingItems(content: String?): Single<ShoppingItems?>
}