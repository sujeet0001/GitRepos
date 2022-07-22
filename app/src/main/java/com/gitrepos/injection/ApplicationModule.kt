package com.gitrepos.injection

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import androidx.room.Room
import com.gitrepos.preferences.PreferenceImplementation
import com.gitrepos.preferences.Preferences
import com.gitrepos.room.RepoDB
import com.gitrepos.utils.ApplicationContext
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class ApplicationModule {

    @Singleton
    @Provides
    fun providesRetrofit() : Retrofit{
        return Retrofit.Builder().baseUrl("https://api.github.com/users/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideRoomDB(@ApplicationContext context: Context): RepoDB {
        return Room.databaseBuilder(
            context,
            RepoDB::class.java, "REPO_DATABASE" // database name need to be stored elsewhere
        )
            .fallbackToDestructiveMigration() // get correct db version if schema changed
            .build()
    }

    @Provides
    @Singleton
    fun providePreferences(application: Application) = application.getSharedPreferences("RepoPrefs", Context.MODE_PRIVATE);

    @Provides
    @Singleton
    fun provideUserPreferences(sharedPreferences: SharedPreferences): Preferences =
        PreferenceImplementation(sharedPreferences)
}