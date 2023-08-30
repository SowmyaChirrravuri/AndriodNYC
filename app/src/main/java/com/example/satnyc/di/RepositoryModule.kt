package com.example.satnyc.di

import com.example.satnyc.repo.ApiInterface
import com.example.satnyc.repo.DataRepository
import com.example.satnyc.repo.DataRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    fun provideDataRepository(apiInterface: ApiInterface): DataRepository {
        return DataRepositoryImpl(apiInterface)
    }
}
