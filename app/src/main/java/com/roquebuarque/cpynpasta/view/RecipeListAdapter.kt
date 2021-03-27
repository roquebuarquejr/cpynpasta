package com.roquebuarque.cpynpasta.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.RoundedCornersTransformation
import com.roquebuarque.cpynpasta.R
import com.roquebuarque.cpynpasta.model.RecipeDto

class RecipeListAdapter() :
    ListAdapter<RecipeDto, RecipeListAdapter.RecipeViewHolder>(RecipeListAdapter) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.item_recipe, parent, false)
        return RecipeViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        val recipe = getItem(position)
        holder.bind()
        holder.txtTitle.text = recipe.title
        holder.txtSourceName.text = recipe.sourceName
        holder.txtReadyInMinutes.text =
            holder.itemView.context.getString(R.string.image_recipe_min, recipe.readyInMinutes)

        holder.imgRecipe.load(recipe.image) {
            crossfade(true)
            placeholder(R.drawable.ic_place_holder)
            transformations(RoundedCornersTransformation(topLeft = 15f, topRight = 15f))
        }
    }

    class RecipeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        lateinit var imgRecipe: ImageView
        lateinit var txtTitle: TextView
        lateinit var txtSourceName: TextView
        lateinit var txtReadyInMinutes: TextView

        fun bind() {
            with(itemView) {
                txtTitle = findViewById(R.id.txtTitleRecipeListItem)
                txtSourceName = findViewById(R.id.txtSourceNameListItem)
                txtReadyInMinutes = findViewById(R.id.txtReadyInMinutesListItem)
                imgRecipe = findViewById(R.id.imgRecipeListItem)
            }
        }
    }


    private companion object : DiffUtil.ItemCallback<RecipeDto>() {

        override fun areItemsTheSame(oldItem: RecipeDto, newItem: RecipeDto): Boolean {
            return oldItem.title == newItem.title
        }

        override fun areContentsTheSame(oldItem: RecipeDto, newItem: RecipeDto): Boolean {
            return oldItem == newItem
        }
    }

}