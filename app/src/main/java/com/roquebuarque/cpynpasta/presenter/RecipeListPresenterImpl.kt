package com.roquebuarque.cpynpasta.presenter

import com.roquebuarque.cpynpasta.R
import com.roquebuarque.cpynpasta.base.LifecycleScope
import com.roquebuarque.cpynpasta.model.NetworkModule
import com.roquebuarque.cpynpasta.model.RecipeService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.lang.Exception
import java.lang.IllegalArgumentException

class RecipeListPresenterImpl(private val service: RecipeService) :
    RecipeListContract.Presenter, LifecycleScope() {

    private var view: RecipeListContract.View? = null

    override fun fetchRandomRecipes() {

        launch {

            view?.displayLoading(true)

            try {
                val response = service.getRecipes()

                view?.displayLoading(false)
                view?.displayRecipes(response.recipes)

            } catch (exception: Exception) {
                exception.printStackTrace()

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

    companion object {

        fun create(service: RecipeService = NetworkModule.createNetworkService())
                : RecipeListPresenterImpl {
            return RecipeListPresenterImpl(service)
        }
    }

}
