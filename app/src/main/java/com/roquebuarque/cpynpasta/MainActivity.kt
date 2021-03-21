package com.roquebuarque.cpynpasta

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val service = NetworkModule.createNetworkService<RecipeService>()
        lifecycleScope.launch {
            val recipes = service.getRecipes()
            Log.d(MainActivity::class.java.name, recipes.toString())
        }
    }
}