package com.umutcansahin.wallpaperapp.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.umutcansahin.wallpaperapp.R
import com.umutcansahin.wallpaperapp.databinding.SelectLayoutBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class SelectFragment: Fragment(R.layout.select_layout) {

    private var fragmentBinding : SelectLayoutBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = SelectLayoutBinding.bind(view)
        fragmentBinding = binding

        binding.buttonCar.setOnClickListener {
            val action = SelectFragmentDirections.actionSelectFragmentToImageFragment("car")
            findNavController().navigate(action)
        }

        binding.buttonView.setOnClickListener {
            val action = SelectFragmentDirections.actionSelectFragmentToImageFragment("view")
            findNavController().navigate(action)
        }

        binding.buttonCats.setOnClickListener {
            val action = SelectFragmentDirections.actionSelectFragmentToImageFragment("cat")
            findNavController().navigate(action)
        }

        binding.buttonDogs.setOnClickListener {
            val action = SelectFragmentDirections.actionSelectFragmentToImageFragment("dog")
            findNavController().navigate(action)
        }

        binding.buttonBuilding.setOnClickListener {
            val action = SelectFragmentDirections.actionSelectFragmentToImageFragment("building")
            findNavController().navigate(action)
        }

        binding.buttonArt.setOnClickListener {
            val action = SelectFragmentDirections.actionSelectFragmentToImageFragment("art")
            findNavController().navigate(action)
        }

        binding.buttonFruit.setOnClickListener {
            val action = SelectFragmentDirections.actionSelectFragmentToImageFragment("fruit")
            findNavController().navigate(action)
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        fragmentBinding = null
    }
}