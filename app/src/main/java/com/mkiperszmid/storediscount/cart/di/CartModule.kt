package com.mkiperszmid.storediscount.cart.di

import com.mkiperszmid.storediscount.cart.domain.usecase.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object CartModule {

    @Singleton
    @Provides
    fun provideCartUseCases(): CartUseCases {
        return CartUseCases(
            calculateDiscounts = CalculateDiscounts(
                bulkDiscount = BulkDiscount(),
                promotionDiscount = PromotionDiscount()
            ),
            calculatePrice = CalculatePrice()
        )
    }
}
