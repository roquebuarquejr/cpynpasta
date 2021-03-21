package com.roquebuarque.cpynpasta

import retrofit2.http.GET

interface RecipeService {

    @GET("recipes/random")
    suspend fun getRecipes(): RecipesResponse

}