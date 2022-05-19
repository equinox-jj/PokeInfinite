package com.pokeinfinite.di

import com.pokeinfinite.data.repository.PokemonRepository
import com.pokeinfinite.data.repository.PokemonRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun provideRepository(repositoryImpl: PokemonRepositoryImpl): PokemonRepository

}