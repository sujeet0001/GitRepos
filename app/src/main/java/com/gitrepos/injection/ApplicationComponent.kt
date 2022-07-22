package com.gitrepos.injection

import android.content.Context
import com.gitrepos.App
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [AndroidSupportInjectionModule::class,
        ApplicationModule::class,
        ViewModelProviderModule::class, BindingActivityModule::class, DBModule::class]
)
interface ApplicationComponent: AndroidInjector<App> {

    @Component.Factory
    interface Factory{
        fun create(@BindsInstance context: Context) : ApplicationComponent
    }

    override fun inject(app: App)
}