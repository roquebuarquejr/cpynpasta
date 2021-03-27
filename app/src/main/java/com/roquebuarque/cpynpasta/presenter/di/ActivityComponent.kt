package com.roquebuarque.cpynpasta.presenter.di

import com.roquebuarque.cpynpasta.application.di.ActivityScope
import com.roquebuarque.cpynpasta.view.MainActivity
import dagger.Subcomponent

@ActivityScope
@Subcomponent(
    modules = [ActivityModule::class]
)
interface ActivityComponent{
    @Subcomponent.Builder
    interface Builder {

        fun activityModule(module: ActivityModule): Builder

        fun build(): ActivityComponent
    }

    fun inject(recipeListActivity: MainActivity)

}