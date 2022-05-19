package com.pokeinfinite.data.model

import com.google.gson.annotations.SerializedName

data class PokemonResponse(
    @SerializedName("count") val count: Int?,
    @SerializedName("next") val next: String?,
    @SerializedName("previous") val previous: Any?,
    @SerializedName("results") val results: List<PokemonResult>
) {
    data class PokemonResult(
        @SerializedName("name") val name: String?,
        @SerializedName("url") val url: String?
    ) {
        fun getPokemonImage(): String {
            val index = url?.split("/".toRegex())?.dropLast(1)?.last()
            return "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/$index.png"
        }
    }
}