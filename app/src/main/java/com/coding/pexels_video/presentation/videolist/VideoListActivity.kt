package com.coding.pexels_video.presentation.videolist

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.coding.pexels_video.MyApplication
import com.coding.pexels_video.R
import com.coding.pexels_video.data.model.Video
import com.coding.pexels_video.presentation.videoplayer.VideoPlayerActivity
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class VideoListActivity : AppCompatActivity(), VideoListContract.View {

    @Inject
    lateinit var videoAdapter: VideoAdapter
    private val presenter: VideoListPresenter by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video_list)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        videoAdapter = VideoAdapter(this::onVideoClicked)
        recyclerView.adapter = videoAdapter
        recyclerView.layoutManager = GridLayoutManager(this, 2) // Display 2 videos per row

        presenter.attachView(this)
        presenter.loadVideos()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView()
    }

    override fun showVideos(videos: List<Video>) {
        videoAdapter.submitList(videos)
    }

    override fun showLoading() {
        // Show a loading indicator (e.g., ProgressBar)
    }

    override fun hideLoading() {
        // Hide the loading indicator
    }

    override fun showError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
    private fun onVideoClicked(video: Video) {
        presenter.onVideoClick(video)
    }

    override fun navigateToVideoPlayer(video: Video) {
        val intent = Intent(this, VideoPlayerActivity::class.java)
        intent.putExtra("video", video)
        startActivity(intent)
    }
}