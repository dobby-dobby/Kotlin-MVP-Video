package com.coding.pexels_video.presentation.videolist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.coding.pexels_video.R
import com.coding.pexels_video.data.model.Video

class VideoAdapter(private val onVideoClick: (Video) -> Unit) :
    ListAdapter<Video, VideoAdapter.VideoViewHolder>(VideoDiffCallback()) {

    inner class VideoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val imageView: ImageView = itemView.findViewById(R.id.videoThumbnail)
        private val titleTextView: TextView = itemView.findViewById(R.id.videoTitle)
        // ... other views

        fun bind(video: Video) {
            // Use Glide or Picasso to load the thumbnail image into imageView
            titleTextView.text = video.title
            // ... set other views

            itemView.setOnClickListener { onVideoClick(video) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_video, parent, false)
        return VideoViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: VideoViewHolder, position: Int) {
        val video = getItem(position)
        holder.bind(video)
    }
}

class VideoDiffCallback : DiffUtil.ItemCallback<Video>() {
    // ... (Implement areItemsTheSame and areContentsTheSame)
    override fun areItemsTheSame(oldItem: Video, newItem: Video): Boolean {
        TODO("Not yet implemented")
    }

    override fun areContentsTheSame(oldItem: Video, newItem: Video): Boolean {
        TODO("Not yet implemented")
    }
}