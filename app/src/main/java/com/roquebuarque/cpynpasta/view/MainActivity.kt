package com.roquebuarque.cpynpasta.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import com.roquebuarque.cpynpasta.R
import com.roquebuarque.cpynpasta.model.RecipeService
import com.roquebuarque.cpynpasta.model.NetworkModule
import com.roquebuarque.cpynpasta.model.RecipeDto
import com.roquebuarque.cpynpasta.presenter.RecipeListContract
import com.roquebuarque.cpynpasta.presenter.RecipeListPresenterImpl
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity(), RecipeListContract.View {

    private val presenter = RecipeListPresenterImpl.create()
    lateinit var txtHello: TextView
    lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        txtHello = findViewById(R.id.helloWorld)
        progressBar = findViewById(R.id.progressBar)

        lifecycle.addObserver(presenter)
        presenter.fetchRandomRecipes()
    }

    override fun onStart() {
        super.onStart()
        presenter.attachView(this)
    }

    override fun onPause() {
        presenter.detachView()
        super.onPause()
    }

    override fun displayRecipes(list: List<RecipeDto>) {
        txtHello.text = list.size.toString()
    }

    override fun displayLoading(isLoading: Boolean) {
            progressBar.isVisible = isLoading
    }

    override fun showError(message: Int) {
        txtHello.setText(message)
    }
}