package com.roquebuarque.cpynpasta.model

import retrofit2.http.GET

interface RecipeService {

    @GET("recipes/random?number=30")
    suspend fun getRecipes(): RecipesResponse

}