package com.pokeinfinite.data.repository

import androidx.paging.PagingData
import com.pokeinfinite.data.model.PokemonResponse
import kotlinx.coroutines.flow.Flow

interface PokemonRepository {

    fun getPokemonPaging(): Flow<PagingData<PokemonResponse.PokemonResult>>

}