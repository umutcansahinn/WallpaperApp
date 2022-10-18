package com.umutcansahin.wallpaperapp.view

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.umutcansahin.wallpaperapp.R
import com.umutcansahin.wallpaperapp.Util.Status
import com.umutcansahin.wallpaperapp.adapter.ImageRecyclerAdapter
import com.umutcansahin.wallpaperapp.databinding.ImageLayoutBinding
import com.umutcansahin.wallpaperapp.viewmodel.ImageFragmentViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class ImageFragment @Inject constructor(
    val imageRecyclerAdapter: ImageRecyclerAdapter
): Fragment(R.layout.image_layout) {

    private var fragmentBinding : ImageLayoutBinding? = null
    private var imageString : String? = null
    lateinit var viewModel: ImageFragmentViewModel


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = ImageLayoutBinding.bind(view)
        fragmentBinding = binding

        viewModel = ViewModelProvider(requireActivity()).get(ImageFragmentViewModel::class.java)

        arguments?.let {
            imageString = ImageFragmentArgs.fromBundle(it).searchImage
        }
        viewModel.takeImageString(imageString!!)

        subcribeToObservers()

        binding.recyclerView.adapter = imageRecyclerAdapter
        binding.recyclerView.layoutManager = GridLayoutManager(requireContext(),2)
    }

    fun subcribeToObservers() {

            viewModel.imageList.observe(viewLifecycleOwner, Observer {
                when(it.status) {
                    Status.SUCCESS -> {
                        val urls = it.data?.hits?.map {
                            it.largeImageURL
                        }
                        imageRecyclerAdapter.images = urls ?: listOf()
                        fragmentBinding?.progressBar?.visibility = View.GONE
                    }
                    Status.LOADING -> {
                        fragmentBinding?.progressBar?.visibility = View.VISIBLE
                    }
                    Status.ERROR -> {
                        Toast.makeText(requireContext(), it.message ?: "Error", Toast.LENGTH_SHORT).show()
                        fragmentBinding?.progressBar?.visibility = View.GONE
                    }
                }
            })

    }


    override fun onDestroy() {
        super.onDestroy()
        fragmentBinding = null
    }
}