package com.coding.pexels_video.domain.usecase

import com.coding.pexels_video.data.model.Video
import com.coding.pexels_video.data.model.VideoResponse
import com.coding.pexels_video.domain.repository.VideoRepository
import com.coding.pexels_video.ulti.Result
import retrofit2.Response
import javax.inject.Inject

class GetVideosUseCase @Inject constructor(private val videoRepository: VideoRepository) {

    suspend operator fun invoke(page: Int, perPage: Int, param: Any): Response<VideoResponse> {
        return videoRepository.getPopularVideos(page, perPage)
    }
}