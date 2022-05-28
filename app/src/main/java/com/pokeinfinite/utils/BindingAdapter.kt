package com.pokeinfinite.utils

import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.graphics.drawable.toBitmap
import androidx.databinding.BindingAdapter
import androidx.navigation.findNavController
import androidx.palette.graphics.Palette
import coil.load
import com.google.android.material.card.MaterialCardView
import com.google.android.material.chip.Chip
import com.pokeinfinite.R
import com.pokeinfinite.data.model.PokemonResult
import com.pokeinfinite.data.model.StatsItem
import com.pokeinfinite.data.model.TypesItem
import com.pokeinfinite.ui.home.HomeFragmentDirections
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.*

object BindingAdapter {

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

    @BindingAdapter("android:stats_pokemon")
    @JvmStatic
    fun detailPokemonStats(view: TextView, data: StatsItem) {
        (data.baseStat.toString() + buildString {
            append(" / 300")
        }).also { view.text = it }
    }

    @BindingAdapter("android:stats_pokemon_progress")
    @JvmStatic
    fun detailPokemonStatsProgress(view: ProgressBar, data: StatsItem) {
        view.secondaryProgress = 255
        view.max = 255
        CoroutineScope(Dispatchers.Main).launch {
            var state = 0
            while (state <= data.baseStat) {
                view.progress = state
                state++
                delay(10)
            }
        }
    }

    @BindingAdapter("android:stat_pokemon_string")
    @JvmStatic
    fun detailPokemonStatsString(view: TextView, data: StatsItem) {
        if (data.stat.name.contains("-")) {
            val first = data.stat.name.substringBefore("-").replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.ROOT) else it.toString() }
            val second = data.stat.name.substringAfter("-").replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.ROOT) else it.toString() }
            "$first - $second".also { view.text = it }
        } else view.text = data.stat.name.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }
    }

    @BindingAdapter("android:pokemon_types")
    @JvmStatic
    fun detailPokemonTypes(view: Chip, data: TypesItem) {
        view.text = data.type.name
        view.setChipBackgroundColorResource(getTypeColor(data))
    }

}