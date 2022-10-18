package com.umutcansahin.wallpaperapp.view

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.RequestManager
import com.umutcansahin.wallpaperapp.R
import com.umutcansahin.wallpaperapp.Util.Status
import com.umutcansahin.wallpaperapp.adapter.ImageRecyclerAdapter
import com.umutcansahin.wallpaperapp.databinding.SingleBigImageBinding
import com.umutcansahin.wallpaperapp.roomdb.Art
import com.umutcansahin.wallpaperapp.viewmodel.SingleBigImageViewModel
import javax.inject.Inject

class SingleBigImageFragment @Inject constructor(
    val glide: RequestManager
): Fragment(R.layout.single_big_image) {

    private var fragmentBinding: SingleBigImageBinding? = null
    lateinit var imageUrl: String
    lateinit var viewModel: SingleBigImageViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(requireActivity()).get(SingleBigImageViewModel::class.java)

        val binding = SingleBigImageBinding.bind(view)
        fragmentBinding = binding

        arguments?.let {
            imageUrl = SingleBigImageFragmentArgs.fromBundle(it).ImageUrl
        }

        glide.load(imageUrl).into(binding.singleBigImage)

        viewModel.saveImage(imageUrl)

    }

    override fun onDestroy() {
        super.onDestroy()
        fragmentBinding = null
    }

}