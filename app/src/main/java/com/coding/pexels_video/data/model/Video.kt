package com.coding.pexels_video.data.model

import android.os.Parcel
import android.os.Parcelable

data class Video(
    val title: String,
    val id: Int,
    val width: Int,
    val height: Int,
    val url: String,
    val image: String,
    val duration: Int,
    val user: User,
    val videoFiles: List<VideoFile>,
    val videoPictures: List<VideoPicture>
) : Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readString().toString(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readInt(),
        TODO("user"),
        TODO("videoFiles"),
        TODO("videoPictures")
    )

    data class User(
        val id: Int,
        val name: String,
        val url: String
    )

    data class VideoFile(
        val id: Int,
        val quality: String,
        val fileType: String,
        val width: Int?, // Nullable because it's null for HLS
        val height: Int?, // Nullable because it's null for HLS
        val link: String
    )

    data class VideoPicture(
        val id: Int,
        val picture: String,
        val nr: Int
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(title)
        parcel.writeInt(id)
        parcel.writeInt(width)
        parcel.writeInt(height)
        parcel.writeString(url)
        parcel.writeString(image)
        parcel.writeInt(duration)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Video> {
        override fun createFromParcel(parcel: Parcel): Video {
            return Video(parcel)
        }

        override fun newArray(size: Int): Array<Video?> {
            return arrayOfNulls(size)
        }
    }
}

data class VideoResponse(
    val page: Int,
    val perPage: Int,
    val totalResults: Int,
    val url: String,
    val videos: List<Video>
)
