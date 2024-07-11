package com.coding.pexels_video.di

import com.coding.pexels_video.data.remote.VideoApi
import com.coding.pexels_video.domain.repository.VideoRepository
import com.coding.pexels_video.domain.usecase.GetVideosUseCase
import com.coding.pexels_video.presentation.videolist.VideoListContract
import com.coding.pexels_video.presentation.videolist.VideoListPresenter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.pexels.com/videos/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideVideoApi(retrofit: Retrofit): VideoApi {
        return retrofit.create(VideoApi::class.java)
    }

    @Provides
    @Singleton
    fun provideVideoRepository(videoApi: VideoApi): VideoRepository {
        return VideoRepository(videoApi)
    }

    @Provides
    @Singleton
    fun provideGetVideosUseCase(videoRepository: VideoRepository): GetVideosUseCase {
        return GetVideosUseCase(videoRepository)
    }

    @Provides
    fun provideVideoListPresenter(getVideosUseCase: GetVideosUseCase): VideoListPresenter {
        return VideoListPresenter(getVideosUseCase)
    }
}