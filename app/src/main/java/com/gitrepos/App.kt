package com.gitrepos

import android.app.Application
import com.gitrepos.injection.ApplicationComponent
import com.gitrepos.injection.DaggerApplicationComponent
import dagger.android.AndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class App: Application(){

    @Inject
    lateinit var appComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerApplicationComponent.factory().create(this)

    }


}