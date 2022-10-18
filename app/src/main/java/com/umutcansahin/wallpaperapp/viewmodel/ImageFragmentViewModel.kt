package com.umutcansahin.wallpaperapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.umutcansahin.wallpaperapp.Util.Resource
import com.umutcansahin.wallpaperapp.model.ImageResponce
import com.umutcansahin.wallpaperapp.repo.ArtRepository
import com.umutcansahin.wallpaperapp.roomdb.Art
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ImageFragmentViewModel @Inject constructor(
    private val repository: ArtRepository
) : ViewModel() {

    private val images = MutableLiveData<Resource<ImageResponce>>()
    val imageList: LiveData<Resource<ImageResponce>>
        get() = images


    fun takeImageString(imageString: String) {
        searchForImage(imageString)

    }

    fun searchForImage(searchString: String) {
        images.value = Resource.loading(null)
        viewModelScope.launch {
            val responce = repository.searchImage(searchString)
            images.value = responce
        }
    }
}