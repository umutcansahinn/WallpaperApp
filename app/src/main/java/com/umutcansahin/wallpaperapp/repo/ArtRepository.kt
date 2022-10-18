package com.umutcansahin.wallpaperapp.repo

import androidx.lifecycle.LiveData
import com.umutcansahin.wallpaperapp.Util.Resource
import com.umutcansahin.wallpaperapp.api.RetofitAPI
import com.umutcansahin.wallpaperapp.model.ImageResponce
import com.umutcansahin.wallpaperapp.roomdb.Art
import com.umutcansahin.wallpaperapp.roomdb.ArtDao
import javax.inject.Inject

class ArtRepository @Inject constructor(
    private val artDao: ArtDao,
    private val retrofitAPI: RetofitAPI
) {

    suspend fun insertArt(art: Art) {
        artDao.insertArt(art)
    }

    suspend fun delete(art: Art) {
        artDao.deleteArt(art)
    }

    fun getArt(): LiveData<List<Art>> {
        return artDao.observeArts()
    }

    suspend fun searchImage(imageString: String): Resource<ImageResponce> {
        return  try {
            val responce = retrofitAPI.imageSearch(imageString)
            if (responce.isSuccessful) {
                responce.body()?.let {
                    return@let Resource.success(it)
                } ?: Resource.error("Error",null)
            }else {
                Resource.error("error",null)
            }
        }catch (e: Exception){
            Resource.error("error",null)
        }
    }
}