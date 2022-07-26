package com.gitrepos

import com.gitrepos.injection.ApplicationComponent
import com.gitrepos.injection.DaggerApplicationComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import javax.inject.Inject

class App: DaggerApplication(){

    @Inject
    lateinit var appComponent: ApplicationComponent

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerApplicationComponent.builder().application(this).build().also {
            it.inject(this)
        }
    }


}