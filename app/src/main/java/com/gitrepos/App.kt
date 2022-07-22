package com.gitrepos

import android.app.Application
import com.gitrepos.injection.ApplicationComponent
import dagger.android.AndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class App: Application(), HasAndroidInjector  {

    @Inject
    lateinit var appComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()



    }

    override fun androidInjector(): AndroidInjector<Any> {
        TODO("Not yet implemented")
    }


}