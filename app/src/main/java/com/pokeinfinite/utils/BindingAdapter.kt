package com.pokeinfinite.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import coil.load
import com.pokeinfinite.R
import com.pokeinfinite.data.model.PokemonResult

class BindingAdapter {
    companion object {
//        @BindingAdapter("android:navigateMovieToDetail")
//        @JvmStatic
//        fun navigateMovieToDetail(view: CardView, movieId: Int) {
//            view.setOnClickListener {
//                val action = MovieFragmentDirections.actionMovieFragmentToMovieDetailFragment(movieId)
//                view.findNavController().navigate(action)
//
//            }
//        }


        @BindingAdapter("android:pokemonPoster")
        @JvmStatic
        fun backdropPath(imageView: ImageView, url: String) {
            imageView.load(url) {
                crossfade(200)
                error(R.drawable.ic_launcher_foreground)
            }
        }
    }

}