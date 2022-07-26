package com.gitrepos.injection

import android.app.Application
import com.gitrepos.App
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Component(
    modules = [AndroidSupportInjectionModule::class,
        ApplicationModule::class,
        ViewModelProviderModule::class, BindingActivityModule::class, DBModule::class]
)
@Singleton
interface ApplicationComponent: AndroidInjector<App>{

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder
        fun build(): ApplicationComponent

    }
    override fun inject(app: App)
}