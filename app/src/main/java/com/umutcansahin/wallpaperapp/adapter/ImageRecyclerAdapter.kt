package com.umutcansahin.wallpaperapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.umutcansahin.wallpaperapp.R
import com.umutcansahin.wallpaperapp.view.ImageFragmentDirections
import javax.inject.Inject

class ImageRecyclerAdapter @Inject constructor(
    val glide: RequestManager
) : RecyclerView.Adapter<ImageRecyclerAdapter.ImageViewHolder>() {

    class ImageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    private val diffUtil = object : DiffUtil.ItemCallback<String>() {

        override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }
    }

        private val recyclerListDiffer = AsyncListDiffer(this,diffUtil)

        var images: List<String>
            get() = recyclerListDiffer.currentList
            set(value) = recyclerListDiffer.submitList(value)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycler_row,parent,false)
        return ImageViewHolder(view)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        val imageView = holder.itemView.findViewById<ImageView>(R.id.recyclerRowImage)
        val url = images[position]

        holder.itemView.apply {
            glide.load(url).into(imageView)
            setOnClickListener {
                    val action = ImageFragmentDirections.actionImageFragmentToSingleBigImageFragment(url)
                    findNavController().navigate(action)
                }
            }
        }


    override fun getItemCount(): Int {
        return images.size
    }
}