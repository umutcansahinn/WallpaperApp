package com.umutcansahin.wallpaperapp.model

data class ImageResponce(

    val hits: List<ImageResult>,
    val total: Int,
    val totalHits: Int
)
