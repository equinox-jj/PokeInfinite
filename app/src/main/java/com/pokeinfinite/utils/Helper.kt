package com.pokeinfinite.utils

import android.annotation.SuppressLint
import java.text.SimpleDateFormat

fun formatId(pokemonId: Int) : String = String.format("#%03d", pokemonId)
fun extractPokemonImage(url: String?): String {
    val index = url?.split("/".toRegex())?.dropLast(1)?.last()
    return "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/$index.png"
}
