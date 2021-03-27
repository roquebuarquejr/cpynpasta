package com.roquebuarque.cpynpasta.application.di

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides

@Module
class ApplicationModule {

    @Provides
    fun provideApplicationContext(application: Application): Context {
        return application
    }

}