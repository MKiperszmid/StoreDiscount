package com.mkiperszmid.storediscount.core.data.di

import com.mkiperszmid.storediscount.core.data.ProductRepositoryImpl
import com.mkiperszmid.storediscount.core.data.remote.ProductApi
import com.mkiperszmid.storediscount.core.domain.repository.ProductRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object ProductModule {

    @Provides
    @Singleton
    fun provideApi(): ProductApi {
        return Retrofit.Builder().baseUrl(ProductApi.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .client(OkHttpClient.Builder().build()).build().create()
    }

    @Provides
    @Singleton
    fun provideRepository(api: ProductApi): ProductRepository {
        return ProductRepositoryImpl(api = api)
    }
}
