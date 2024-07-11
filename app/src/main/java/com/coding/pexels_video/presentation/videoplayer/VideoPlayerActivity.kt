package com.coding.pexels_video.presentation.videoplayer

import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.OptIn
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.media3.common.MediaItem
import androidx.media3.common.util.UnstableApi
import androidx.media3.common.util.Util
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView
import com.coding.pexels_video.MyApplication
import com.coding.pexels_video.R
import com.coding.pexels_video.data.model.Video

class VideoPlayerActivity : AppCompatActivity(), VideoPlayerContract.View {
    private lateinit var presenter: VideoPlayerContract.Presenter
    private var player: ExoPlayer? = null
    private lateinit var playerView: PlayerView
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    val video = intent.getParcelableExtra("video", Video::class.java)

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video_player)

        playerView = findViewById(R.id.playerView)

        val video = intent.getParcelableExtra("video", Video::class.java)
        presenter = VideoPlayerPresenter(video!!, this) // Pass video data and context

        presenter.attachView(this)
        presenter.setVideo(video) // Immediately set the video for playback
    }

    override fun initializePlayer(videoUrl: String) {
        player = ExoPlayer.Builder(this).build().also { exoPlayer ->
            playerView.player = exoPlayer
            val mediaItem = MediaItem.fromUri(videoUrl)
            exoPlayer.setMediaItem(mediaItem)
            exoPlayer.playWhenReady = true
            exoPlayer.prepare()
        }
    }

    override fun releasePlayer() {
        player?.release()
        player = null
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    @OptIn(UnstableApi::class)
    override fun onStart() {
        super.onStart()
        if (Util.SDK_INT >= 24) {
            initializePlayer(video?.videoFiles?.get(0)?.link ?: "")
        }
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    @OptIn(UnstableApi::class)
    override fun onResume() {
        super.onResume()
        if ((Util.SDK_INT < 24 || player == null)) {
            initializePlayer(video?.videoFiles?.get(0)?.link ?: "")
        }
    }

    @OptIn(UnstableApi::class)
    override fun onPause() {
        super.onPause()
        if (Util.SDK_INT < 24) {
            releasePlayer()
        }
    }

    @OptIn(UnstableApi::class)
    override fun onStop() {
        super.onStop()
        if (Util.SDK_INT >= 24) {
            releasePlayer()
        }
    }

    override fun showLoading() {
        // TODO: Show loading indicator (e.g., ProgressBar)
    }

    override fun hideLoading() {
        // TODO: Hide loading indicator
    }

    override fun showError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun playVideo() {
        player?.play()
    }

    override fun pauseVideo() {
        player?.pause()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView()
    }
}