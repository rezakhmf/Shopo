package com.farahaniconsulting.shopo.di.modules

import com.farahaniconsulting.shopo.data.network.ApiService
import com.farahaniconsulting.shopo.data.repository.ShopoRepository
import com.farahaniconsulting.shopo.data.repository.local.ShoppingItemsLocalRepository
import com.farahaniconsulting.shopo.data.repository.remote.ShoppingItemsRemoteRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun provideShoppingItemsRemoteRepository(apiService: ApiService) =
        ShoppingItemsRemoteRepository(apiService)

    @Provides
    @Singleton
    fun provideShoppingItemsLocalRepository() =
        ShoppingItemsLocalRepository()
}