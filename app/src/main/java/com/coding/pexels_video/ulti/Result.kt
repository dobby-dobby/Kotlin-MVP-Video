package com.coding.pexels_video.ulti

sealed class Result<out T> { // T is the type of data we expect to return on success
    data class Success<out T>(val data: T) : Result<T>()
    data class Error(val message: String) : Result<Nothing>()
}