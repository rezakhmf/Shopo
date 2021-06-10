package com.farahaniconsulting.shopo.di.modules

import com.farahaniconsulting.shopo.data.repository.ShopoRepository
import com.farahaniconsulting.shopo.domain.shoppingITems.GetShoppingItemsUC
import dagger.Module
import dagger.Provides
import io.reactivex.Scheduler
import javax.inject.Named
import javax.inject.Singleton

@Module
class UseCaseModule {

    @Provides
    @Singleton
    fun provideGetUpComingLaunchesUC(
        repository: ShopoRepository,
        @Named(SUBSCRIBER_ON) backgroundScheduler: Scheduler
    ): GetShoppingItemsUC =
        GetShoppingItemsUC(
            repository,
            backgroundScheduler)
}