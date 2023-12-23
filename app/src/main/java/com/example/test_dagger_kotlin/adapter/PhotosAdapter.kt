package com.example.test_dagger_kotlin.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import coil.load
import com.example.test_dagger_kotlin.adapter.PhotosAdapter.PhotoViewHolder
import com.example.test_dagger_kotlin.databinding.PhotosListItemsBinding
import com.example.test_dagger_kotlin.interfaces.RecyclerViewEvent
import com.example.test_dagger_kotlin.models.Photos

class PhotosAdapter(private val listener: RecyclerViewEvent) : RecyclerView.Adapter<PhotoViewHolder>() {

    private val data = mutableListOf<Photos>()

    class PhotoViewHolder(private val photosListItemsBinding: PhotosListItemsBinding) :
        ViewHolder(photosListItemsBinding.root) {

        fun bind(photos: Photos) {
            photosListItemsBinding.imageView.load(photos.thumbnailUrl)
            photosListItemsBinding.photoId.text = photos.id.toString()
            photosListItemsBinding.albumId.text = photos.albumId.toString()
            photosListItemsBinding.photoTitle.text = photos.title
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        val binding = PhotosListItemsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PhotoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        val item = data[position]
        holder.bind(item)

        holder.itemView.setOnClickListener {
            listener.onItemClick(position, item)
        }
    }

    override fun getItemCount(): Int = data.size

    @SuppressLint("NotifyDataSetChanged")
    fun updateData(newDataList: List<Photos>) {
        data.clear()
        data.addAll(newDataList)
        notifyDataSetChanged()
    }
}