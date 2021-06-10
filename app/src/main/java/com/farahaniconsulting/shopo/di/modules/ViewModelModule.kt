package com.farahaniconsulting.shopo.di.modules

import androidx.lifecycle.ViewModel
import com.farahaniconsulting.shopo.domain.shoppingITems.GetShoppingItemsUC
import com.farahaniconsulting.shopo.ui.shoppingitems.ShoppingItemsViewModel
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import io.reactivex.Scheduler
import javax.inject.Named
import javax.inject.Provider

@Module
class ViewModelModule {

    @Provides
    fun providesViewModelFactory(
        viewModelMap: Map<Class<out ViewModel>,
                @JvmSuppressWildcards Provider<ViewModel>>
    ): ViewModelFactory {
        return ViewModelFactory(viewModelMap)
    }

    @Provides
    @IntoMap
    @ViewModelKey(ShoppingItemsViewModel::class)
    fun provideShoppingItemsViewModel(
        getUpComingLaunchUseCase: GetShoppingItemsUC,
        @Named(OBSERVER_ON) observerOn: Scheduler
    ): ViewModel {
        return ShoppingItemsViewModel(
            getUpComingLaunchUseCase, observerOn
        )
    }
}