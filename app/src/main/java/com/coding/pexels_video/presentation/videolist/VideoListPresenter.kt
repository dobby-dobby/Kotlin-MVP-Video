package com.coding.pexels_video.presentation.videolist

import androidx.lifecycle.ViewModel
import com.coding.pexels_video.data.model.Video
import com.coding.pexels_video.domain.usecase.GetVideosUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class VideoListPresenter @Inject constructor(
    private val getVideosUseCase: GetVideosUseCase
) :  ViewModel(), VideoListContract.Presenter {

    private var view: VideoListContract.View? = null
    private var currentPage = 1
    private val perPage = 15 // Define PER_PAGE as a constant

    @OptIn(DelicateCoroutinesApi::class)
    override fun loadVideos() {
        view?.showLoading()
        GlobalScope.launch(Dispatchers.Main) {
            getVideosUseCase(currentPage, perPage, object : VideoCallback {
                override fun onSuccess(videos: List<Video>) {
                    view?.hideLoading()
                    view?.showVideos(videos)
                    currentPage++
                }

                override fun onError(message: String) {
                    view?.hideLoading()
                    view?.showError(message)
                }
            })
        }
    }

    override fun onVideoClick(video: Video) {
        TODO("Not yet implemented")
    }

    override fun attachView(view: VideoListContract.View) {
        TODO("Not yet implemented")
    }

    override fun detachView() {
        TODO("Not yet implemented")
    }
}

interface VideoCallback {
    fun onSuccess(videos: List<Video>)
    fun onError(message: String)
}