package com.pokeinfinite.data.model

import com.google.gson.annotations.SerializedName

data class PokemonResponse(
    @field:SerializedName("results") val results: List<PokemonResult>
)

data class PokemonResult(

    @field:SerializedName("name") val name: String,
    @field:SerializedName("url") val url: String

)
