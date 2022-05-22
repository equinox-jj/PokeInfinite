package com.pokeinfinite.data.model

import com.google.gson.annotations.SerializedName

data class PokemonResponse(

    @field:SerializedName("next") val next: String? = null,
    @field:SerializedName("previous") val previous: Any? = null,
    @field:SerializedName("count") val count: Int? = null,
    @field:SerializedName("results") val results: List<PokemonResult>

)

data class PokemonResult(

    @field:SerializedName("name") val name: String? = null,
    @field:SerializedName("url") val url: String? = null

) {
    fun extractPokemonImage(): String {
        val index = url?.split("/".toRegex())?.dropLast(1)?.last()
        return "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/$index.png"
    }
}
