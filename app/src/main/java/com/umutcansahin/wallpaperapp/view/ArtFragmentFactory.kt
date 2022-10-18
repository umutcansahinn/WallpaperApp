package com.umutcansahin.wallpaperapp.view

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.bumptech.glide.RequestManager
import com.umutcansahin.wallpaperapp.adapter.ImageRecyclerAdapter
import javax.inject.Inject

class ArtFragmentFactory @Inject constructor(
    private val imageRecyclerAdapter: ImageRecyclerAdapter,
    private val glide: RequestManager
) : FragmentFactory(){

    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
        return when(className) {
            ImageFragment::class.java.name -> ImageFragment(imageRecyclerAdapter)
            SingleBigImageFragment::class.java.name -> SingleBigImageFragment(glide)
            else -> super.instantiate(classLoader, className)
        }
    }
}