package com.roquebuarque.cpynpasta.presenter

import android.util.Log
import com.roquebuarque.cpynpasta.R
import com.roquebuarque.cpynpasta.application.di.ActivityScope
import com.roquebuarque.cpynpasta.base.LifecycleScope
import com.roquebuarque.cpynpasta.model.NetworkModule
import com.roquebuarque.cpynpasta.model.RecipeService
import com.roquebuarque.cpynpasta.presenter.di.ActivityComponent
import dagger.Component
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.lang.Exception
import java.lang.IllegalArgumentException
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

    companion object{
        fun create(service: RecipeService = NetworkModule.createNetworkService()): RecipeListPresenterImpl {
            return RecipeListPresenterImpl(service)
        }
    }

}
