package com.pokeinfinite.di

import com.pokeinfinite.data.repository.PokemonRepository
import com.pokeinfinite.data.repository.PokemonRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun provideRepository(repositoryImpl: PokemonRepositoryImpl): PokemonRepository

}