package com.gitrepos.injection

import com.gitrepos.detail.DetailActivity
import com.gitrepos.detail.di.DetailActivityModule
import com.gitrepos.injection.scope.PerActivity
import com.gitrepos.main.MainActivity
import com.gitrepos.main.di.MainActivityModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class BindingActivityModule {

    @PerActivity
    @ContributesAndroidInjector(modules = [MainActivityModule::class])
    internal abstract fun contributeMainActivity(): MainActivity

    @PerActivity
    @ContributesAndroidInjector(modules = [DetailActivityModule::class])
    internal abstract fun contributeDetailActivity(): DetailActivity

}