package com.coding.pexels_video.presentation.videoplayer

import com.coding.pexels_video.data.model.Video

interface VideoPlayerContract {
    interface View {
        fun initializePlayer(videoUrl: String)
        fun releasePlayer()
        fun showLoading()
        fun hideLoading()
        fun showError(message: String)
        fun playVideo()
        fun pauseVideo()
    }

    interface Presenter {
        fun setVideo(video: Video)
        fun playVideo()
        fun pauseVideo()
        // Add other playback control methods as needed (seek, etc.)
        fun attachView(view: View)
        fun detachView()
    }
}