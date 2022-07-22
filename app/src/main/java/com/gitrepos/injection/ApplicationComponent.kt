package com.gitrepos.injection

import android.content.Context
import android.content.SharedPreferences
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [AndroidSupportInjectionModule::class,
        ApplicationModule::class,
        ViewModelProviderModule::class, BindingActivityModule::class, DBModule::class]
)
interface ApplicationComponent {

    fun getSharedPreference(): SharedPreferences

    @Component.Factory
    interface Factory{
        fun create(@BindsInstance context: Context) : ApplicationComponent
    }
}