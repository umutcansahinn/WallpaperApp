package com.umutcansahin.wallpaperapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.umutcansahin.wallpaperapp.Util.Resource
import com.umutcansahin.wallpaperapp.repo.ArtRepository
import com.umutcansahin.wallpaperapp.roomdb.Art
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SingleBigImageViewModel @Inject constructor(
    private val repository: ArtRepository
) : ViewModel() {

    private fun insertArt(art: Art) = viewModelScope.launch {
        repository.insertArt(art)
    }

    fun saveImage(imageUrl: String) {
        val art = Art(imageUrl,null)
        insertArt(art)
    }


}