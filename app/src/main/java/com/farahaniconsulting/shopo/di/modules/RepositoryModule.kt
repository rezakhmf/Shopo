package com.farahaniconsulting.shopo.di.modules

import com.farahaniconsulting.shopo.data.network.ApiService
import com.farahaniconsulting.shopo.data.repository.REPOSITORY_LOCAL
import com.farahaniconsulting.shopo.data.repository.REPOSITORY_REMOTE
import com.farahaniconsulting.shopo.data.repository.ShopoRepository
import com.farahaniconsulting.shopo.data.repository.local.ShoppingItemsLocalRepository
import com.farahaniconsulting.shopo.data.repository.remote.ShoppingItemsRemoteRepository
import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    @Singleton
    @Named(REPOSITORY_REMOTE)
    fun provideShoppingItemsRemoteRepository(apiService: ApiService) : ShopoRepository =
        ShoppingItemsRemoteRepository(apiService)

    @Provides
    @Singleton
    @Named(REPOSITORY_LOCAL)
    fun provideShoppingItemsLocalRepository() : ShopoRepository =
        ShoppingItemsLocalRepository()
}