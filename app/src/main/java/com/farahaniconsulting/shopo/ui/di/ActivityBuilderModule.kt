package com.farahaniconsulting.shopo.ui.di

import com.farahaniconsulting.shopo.ui.main.MainActivity
import com.farahaniconsulting.shopo.ui.main.di.MainFragmentModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilderModule {
    @ContributesAndroidInjector(modules = [MainFragmentModule::class])
    abstract fun bindMain() : MainActivity
}