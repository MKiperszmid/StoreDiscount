package com.mkiperszmid.storediscount.core.data.di

import android.app.Application
import androidx.room.Room
import androidx.room.RoomDatabase
import com.mkiperszmid.storediscount.core.data.ProductRepositoryImpl
import com.mkiperszmid.storediscount.core.data.local.ProductDao
import com.mkiperszmid.storediscount.core.data.local.StoreDatabase
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
    fun provideDao(application: Application): ProductDao {
        val db = Room.databaseBuilder(
            application,
            StoreDatabase::class.java,
            "cart_db"
        ).build()
        return db.dao
    }

    @Provides
    @Singleton
    fun provideRepository(api: ProductApi, dao: ProductDao): ProductRepository {
        return ProductRepositoryImpl(api = api, dao = dao)
    }
}
