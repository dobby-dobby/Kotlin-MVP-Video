package com.coding.pexels_video.presentation.videoplayer

import android.content.Context
import com.coding.pexels_video.data.model.Video

class VideoPlayerPresenter (
    private var video: Video,
    private val context: Context
) : VideoPlayerContract.Presenter {

    private var view: VideoPlayerContract.View? = null

    override fun setVideo(video: Video) {
        this.video = video
        if (video.videoFiles.isNotEmpty()) { // Check if the list is not empty
            view?.initializePlayer(video.videoFiles[0].link) // Get the link from the first VideoFile object
        } else {
            // Handle the case where there are no video files available (e.g., show an error message)
            view?.showError("No video files available")
        }
    }

    override fun playVideo() {
        view?.playVideo()
    }

    override fun pauseVideo() {
        view?.pauseVideo()
    }

    // ... other playback controls

    override fun attachView(view: VideoPlayerContract.View) {
        this.view = view
    }

    override fun detachView() {
        this.view = null
    }
}