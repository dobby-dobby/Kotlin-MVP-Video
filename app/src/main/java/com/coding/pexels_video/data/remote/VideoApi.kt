package com.coding.pexels_video.data.remote

import com.coding.pexels_video.data.model.VideoResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface VideoApi {
    @Headers("Authorization: YOUR_API_KEY") // Replace with your actual Pexels API key
    @GET("popular") // Or use "search" for search functionality
    suspend fun getPopularVideos(
        @Query("per_page") perPage: Int = 15,
        @Query("page") page: Int = 1
    ): Response<VideoResponse>
}