package com.roquebuarque.cpynpasta.application

import android.app.Application
import com.roquebuarque.cpynpasta.application.di.DependencyGraph

class RecipeApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        DependencyGraph.initialize(this)
    }

}