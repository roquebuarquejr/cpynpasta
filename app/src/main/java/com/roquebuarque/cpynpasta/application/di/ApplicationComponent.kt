package com.roquebuarque.cpynpasta.application.di

import android.app.Application
import com.roquebuarque.cpynpasta.presenter.di.ActivityComponent
import dagger.Component

@ApplicationScope
@Component(modules = [ApplicationModule::class, NetworkModule::class])
interface ApplicationComponent {

    @Component.Builder
    interface Builder {
        fun build(): ApplicationComponent
    }

    fun inject(app: Application)

    fun activityComponentBuilder(): ActivityComponent.Builder

}