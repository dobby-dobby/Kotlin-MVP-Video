package com.coding.pexels_video.presentation.videolist

import com.coding.pexels_video.data.model.Video

interface VideoListContract {
    interface View {
        fun showVideos(videos: List<Video>)
        fun showLoading()
        fun hideLoading()
        fun showError(message: String)
        fun navigateToVideoPlayer(video: Video)
    }

    interface Presenter {
        fun loadVideos()
        fun onVideoClick(video: Video)
        fun attachView(view: View)
        fun detachView()
    }
}