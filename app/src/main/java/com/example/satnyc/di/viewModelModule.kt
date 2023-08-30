package com.example.satnyc.di

import com.example.satnyc.Model.ApiInterface
import com.example.satnyc.Model.DataRepository
import com.example.satnyc.Model.DataRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object ViewModelModule {
    @Provides
    fun provideDataRepository(apiInterface: ApiInterface): DataRepository {
        return DataRepositoryImpl(apiInterface)
    }
}
