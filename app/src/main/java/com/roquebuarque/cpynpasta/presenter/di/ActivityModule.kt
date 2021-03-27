package com.roquebuarque.cpynpasta.presenter.di

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import com.roquebuarque.cpynpasta.application.di.ActivityScope
import dagger.Module
import dagger.Provides

@Module
class ActivityModule(var activity: AppCompatActivity) {


    @Provides
    @ActivityScope
    internal fun provideContext(): Context {
        return activity
    }

}