package com.pokeinfinite.utils

import android.widget.ImageView
import androidx.core.graphics.drawable.toBitmap
import androidx.databinding.BindingAdapter
import androidx.navigation.findNavController
import androidx.palette.graphics.Palette
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


//        @BindingAdapter("android:pokemon_poster")
//        @JvmStatic
//        fun backdropPath(view: ImageView, url: String) {
//            view.load(extractPokemonImage(url)) {
//                crossfade(200)
//                error(R.drawable.ic_launcher_foreground)
//            }
//        }

        @BindingAdapter("android:palette_image", "android:palette_card")
        @JvmStatic
        fun paletteColor(view: ImageView, url: String, paletteCard: MaterialCardView) {
            view.load(extractPokemonImage(url)) {
                allowHardware(false)
                crossfade(200)
                error(R.drawable.ic_launcher_foreground)
                listener(
                    onSuccess = { _, result ->
                        Palette.Builder(result.drawable.toBitmap()).generate() { palette ->
                            val rgb = palette?.dominantSwatch?.rgb
                            if (rgb != null) {
                                paletteCard.setCardBackgroundColor(rgb)
                            }
                        }
                    }
                )
            }
        }


    }
}