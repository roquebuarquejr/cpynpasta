package com.roquebuarque.cpynpasta.presenter

import com.roquebuarque.cpynpasta.R
import com.roquebuarque.cpynpasta.application.di.ActivityScope
import com.roquebuarque.cpynpasta.base.LifecycleScope
import com.roquebuarque.cpynpasta.model.RecipeService
import kotlinx.coroutines.launch
import javax.inject.Inject

@ActivityScope
class RecipeListPresenterImpl @Inject constructor(private val service: RecipeService)
    : RecipeListContract.Presenter, LifecycleScope() {

    private var view: RecipeListContract.View? = null

    override fun fetchRandomRecipes() {

        launch {
            view?.displayLoading(true)
            try {
                val response = service.getRecipes()

                view?.displayLoading(false)
                view?.displayRecipes(response.recipes)

            }catch (exception: Exception){
                view?.displayLoading(false)
                view?.showError(R.string.error_message)
            }
        }

    }

    override fun attachView(view: RecipeListContract.View) {
        this.view = view
    }

    override fun detachView() {
       this.view = null
    }

}
