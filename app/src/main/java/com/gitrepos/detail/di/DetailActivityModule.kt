package com.gitrepos.detail.di

import androidx.lifecycle.ViewModel
import com.gitrepos.detail.DetailViewModel
import com.gitrepos.injection.scope.PerActivity
import com.gitrepos.main.di.MainActivityModule
import com.gitrepos.utils.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import javax.inject.Inject

@Module
abstract class DetailActivityModule {

    @Binds
    @IntoMap
    @ViewModelKey(DetailViewModel::class)
    @PerActivity
    abstract fun bindDetailViewModel(detailViewModel: DetailViewModel): ViewModel

}