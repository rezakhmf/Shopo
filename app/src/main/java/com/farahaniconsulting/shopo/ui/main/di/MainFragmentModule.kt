package com.farahaniconsulting.shopo.ui.main.di

import com.farahaniconsulting.shopo.ui.shoppingitems.list.ShoppingItemsListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainFragmentModule {

    @ContributesAndroidInjector
    abstract fun provideMainFragmentFactory(): ShoppingItemsListFragment
}