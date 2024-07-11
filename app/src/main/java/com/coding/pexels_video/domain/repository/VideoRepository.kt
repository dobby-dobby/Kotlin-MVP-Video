package com.coding.pexels_video.domain.repository

import com.coding.pexels_video.data.model.Video
import com.coding.pexels_video.data.model.VideoResponse
import com.coding.pexels_video.data.remote.VideoApi
import com.coding.pexels_video.ulti.Result
import retrofit2.Response
import javax.inject.Inject

class VideoRepository @Inject constructor(private val videoRemoteDataSource: VideoApi) {

    suspend fun getPopularVideos(page: Int, perPage: Int): Response<VideoResponse> {
        return videoRemoteDataSource.getPopularVideos(page, perPage) // Fetch from remote
    }
}