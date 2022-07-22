package com.gitrepos.injection

import androidx.lifecycle.ViewModelProvider
import com.gitrepos.utils.ViewModelFactory
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelProviderModule {

    @Binds
    internal abstract fun bindViewFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory
}