package com.farahaniconsulting.shopo.di.component

import android.app.Application
import com.farahaniconsulting.shopo.ShopoApplication
import com.farahaniconsulting.shopo.di.modules.*
import com.farahaniconsulting.shopo.ui.di.ActivityBuilderModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        AndroidSupportInjectionModule::class,
        NetModule::class,
        RepositoryModule::class,
        ViewModelModule::class,
        UseCaseModule::class,
        RxJavaModule::class,
        ActivityBuilderModule::class
    ]
)
interface AppComponent : AndroidInjector<ShopoApplication> {

    @Component.Builder
    interface Builder {
        fun build(): AppComponent

        @BindsInstance
        fun application(application: Application) : Builder
    }

    fun inject(application: Application)
}