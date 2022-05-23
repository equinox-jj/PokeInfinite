package com.pokeinfinite.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.navigation.findNavController
import coil.load
import com.google.android.material.card.MaterialCardView
import com.pokeinfinite.R
import com.pokeinfinite.ui.home.HomeFragmentDirections

class BindingAdapter {
    companion object {

        @BindingAdapter("android:navigate_to_pokemon_detail")
        @JvmStatic
        fun navigateMovieToDetail(view: MaterialCardView, pokemonId: String) {
            view.setOnClickListener {
                val action = HomeFragmentDirections.actionHomeFragmentToDetailFragment(pokemonId)
                view.findNavController().navigate(action)
            }
        }


        @BindingAdapter("android:pokemon_poster")
        @JvmStatic
        fun backdropPath(view: ImageView, url: String) {
            view.load(url) {
                crossfade(200)
                error(R.drawable.ic_launcher_foreground)
            }
        }

    }
}