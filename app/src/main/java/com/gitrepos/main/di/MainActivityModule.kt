package com.gitrepos.main.di

import androidx.lifecycle.ViewModel
import com.gitrepos.data.main.service.MainActivityService
import com.gitrepos.injection.scope.PerActivity
import com.gitrepos.main.MainViewModel
import com.gitrepos.utils.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import retrofit2.Retrofit

@Module(
    includes = [MainActivityModule.MainActivityProviderModule::class]
)
abstract class MainActivityModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    @PerActivity
    abstract fun bindMainViewModel(mainActivityViewModel: MainViewModel): ViewModel

    @Module
    object MainActivityProviderModule {
        @Provides
        @PerActivity
        internal fun bindMainActivityService(retrofit: Retrofit): MainActivityService {
            return retrofit.create(MainActivityService::class.java)
        }
    }

}