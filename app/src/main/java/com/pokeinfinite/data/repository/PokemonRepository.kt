package com.pokeinfinite.data.repository

import androidx.paging.PagingData
import com.pokeinfinite.data.model.PokemonResult
import kotlinx.coroutines.flow.Flow

interface PokemonRepository {

    fun getPokemonPaging(): Flow<PagingData<PokemonResult>>

}