package com.umutcansahin.wallpaperapp.api


import com.umutcansahin.wallpaperapp.Util.Util.API_KEY
import com.umutcansahin.wallpaperapp.model.ImageResponce
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RetofitAPI {

    @GET("/api/")
    suspend fun imageSearch(
        @Query("q")
        searchQuery: String,

        @Query("key")
        apiKey: String = API_KEY
    ): Response<ImageResponce>
}