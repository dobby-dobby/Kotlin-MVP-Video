package com.coding.pexels_video.presentation.videolist

import com.coding.pexels_video.domain.usecase.GetVideosUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
object VideoListModule {
    @Provides
    fun provideVideoListPresenter(getVideosUseCase: GetVideosUseCase): VideoListPresenter {
        return VideoListPresenter(getVideosUseCase)
    }
}