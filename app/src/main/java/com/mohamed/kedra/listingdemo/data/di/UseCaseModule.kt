package com.mohamed.kedra.listingdemo.data.di

import com.mohamed.kedra.listingdemo.domain.repository.LaunchesRepository
import com.mohamed.kedra.listingdemo.domain.usecase.details.GetLaunchDetailsUseCase
import com.mohamed.kedra.listingdemo.domain.usecase.details.GetLaunchDetailsUseCaseImpl
import com.mohamed.kedra.listingdemo.domain.usecase.getAllLaunches.GetAllLaunchesUseCase
import com.mohamed.kedra.listingdemo.domain.usecase.getAllLaunches.GetAllLaunchesUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    fun provideGetAllLaunchesUseCase(launchesRepository: LaunchesRepository) : GetAllLaunchesUseCase {
        return GetAllLaunchesUseCaseImpl(launchesRepository)
    }


    @Provides
    fun provideGetGetLaunchDetailsUseCase(launchesRepository: LaunchesRepository) : GetLaunchDetailsUseCase{
        return GetLaunchDetailsUseCaseImpl(launchesRepository)
    }
}