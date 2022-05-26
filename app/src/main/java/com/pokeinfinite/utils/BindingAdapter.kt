package com.pokeinfinite.utils

import android.widget.ImageView
import android.widget.TextView
import androidx.core.graphics.drawable.toBitmap
import androidx.databinding.BindingAdapter
import androidx.navigation.findNavController
import androidx.palette.graphics.Palette
import coil.load
import com.google.android.material.card.MaterialCardView
import com.pokeinfinite.R
import com.pokeinfinite.data.model.PokemonResult
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

        @BindingAdapter("android:palette_image", "android:palette_card")
        @JvmStatic
        fun homePokeImagePalette(view: ImageView, url: String, paletteCard: MaterialCardView) {
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

        @BindingAdapter("android:lower_to_upper")
        @JvmStatic
        fun homeLowerToUpperCase(view: TextView, data: PokemonResult) {
            view.text = data.name.replaceFirstChar { it.uppercase() }
        }

//        @BindingAdapter("android:detail_image_poke")
//        @JvmStatic
//        fun detailPokeImage(image: ImageView, url: String?) {
//            image.load(url) {
//                crossfade(200)
//                error(R.drawable.ic_launcher_foreground)
//            }
//        }

//        @BindingAdapter("android:pokemon_poster")
//        @JvmStatic
//        fun backdropPath(view: ImageView, url: String) {
//            view.load(extractPokemonImage(url)) {
//                crossfade(200)
//                error(R.drawable.ic_launcher_foreground)
//            }
//        }

//        @JvmStatic
//        @BindingAdapter("bindPokemonTypes")
//        fun bindPokemonTypes(recyclerView: RecyclerView, types: List<TypesItem>?) {
//            if (types != null) {
//                recyclerView.clear()
//                for (type in it) {
//                    with(recyclerView) {
//                        addRibbon(
//                            ribbonView(context) {
//                                setText(type.type.name)
//                                setTextColor(Color.WHITE)
//                                setPaddingLeft(84f)
//                                setPaddingRight(84f)
//                                setPaddingTop(2f)
//                                setPaddingBottom(10f)
//                                setTextSize(16f)
//                                setRibbonRadius(120f)
//                                setTextStyle(Typeface.BOLD)
//                                setRibbonBackgroundColorResource(
//                                    PokemonTypeUtils.getTypeColor(type.type.name)
//                                )
//                            }.apply {
//                                maxLines = 1
//                                gravity = Gravity.CENTER
//                            }
//                        )
//                        addItemDecoration(SpacesItemDecoration())
//                    }
//                }
//            }
//        }

//        @BindingAdapter("android:pokemon_type")
//        @JvmStatic
//        fun pokemonType(view: TextView, data: List<TypesItem>) {
//            if (data.size > 3) {
//                view.text = data[1].type.name
//                view.visibility = View.VISIBLE
//            } else {
//                view.visibility = View.GONE
//            }
//        }


    }
}