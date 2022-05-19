package com.pokeinfinite.data.network

import com.pokeinfinite.data.model.PokemonResponse
import com.pokeinfinite.data.model.SinglePokemonResponse
import com.pokeinfinite.utils.Constants.QUERY_LIMIT
import com.pokeinfinite.utils.Constants.QUERY_OFFSET
import com.pokeinfinite.utils.Constants.QUERY_POKEMON_NAME
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("pokemon/")
    suspend fun getPokemonResponse(
        @Query(QUERY_OFFSET) queryOffset: Int,
        @Query(QUERY_LIMIT) queryLimit: Int
    ): PokemonResponse

    @GET("pokemon/{name}")
    suspend fun getSinglePokemonResponse(
        @Path(QUERY_POKEMON_NAME) queryName: String
    ): SinglePokemonResponse

}