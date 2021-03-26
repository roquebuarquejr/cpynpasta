package com.roquebuarque.cpynpasta.presenter

import androidx.annotation.StringRes
import com.roquebuarque.cpynpasta.base.BaseContract
import com.roquebuarque.cpynpasta.model.RecipeDto

interface RecipeListContract : BaseContract {

    interface Presenter : BaseContract.Presenter<View> {

        fun fetchRandomRecipes()
    }

    interface View : BaseContract.View {

        fun displayRecipes(list: List<RecipeDto>)

        fun displayLoading(isLoading: Boolean)

        fun showError(@StringRes message: Int)
    }
}