package com.coding.pexels_video.data.remote

import com.coding.pexels_video.data.model.Video
import com.coding.pexels_video.ulti.Result
import javax.inject.Inject

class VideoRemoteDataSource @Inject constructor(private val videoApi: VideoApi) {
    suspend fun getPopularVideos(page: Int, perPage: Int): Result<List<Video>> {
        return try {
            val response = videoApi.getPopularVideos(perPage, page)
            if (response.isSuccessful) {
                val videoResponse = response.body()
                Result.Success(videoResponse?.videos ?: emptyList())
            } else {
                Result.Error("Error fetching videos: ${response.message()}")
            }
        } catch (e: Exception) {
            Result.Error("Network error: ${e.message}")
        }
    }
}