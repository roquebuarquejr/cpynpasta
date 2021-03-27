package com.roquebuarque.cpynpasta.application.di

import android.app.Application
import com.roquebuarque.cpynpasta.BuildConfig

object DependencyGraph {

    private lateinit var component: ApplicationComponent

    fun initialize(app: Application) {
        if (BuildConfig.DEBUG && this::component.isInitialized) {
            error("Assertion failed")
        }

        component = DaggerApplicationComponent
            .builder()
            .build()
            .apply {
                inject(app)
            }
    }

    fun getComponent() = component
}