package com.example.test_dagger_kotlin.fragments

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavArgs
import androidx.navigation.fragment.navArgs
import coil.load
import com.example.test_dagger_kotlin.viewmodel.PhotoViewModel
import com.example.test_dagger_kotlin.R
import com.example.test_dagger_kotlin.databinding.FragmentPhotoBinding
import com.example.test_dagger_kotlin.models.Photos
import com.example.test_dagger_kotlin.navargsmodel.PhotosArgs

class PhotoFragment : Fragment() {
    private lateinit var _binding : FragmentPhotoBinding

    private val binding get() = _binding

    private val photoViewModel: PhotoViewModel by viewModels()

    private val args by navArgs<PhotoFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPhotoBinding.inflate(inflater, container, false)

        val currentPhoto: PhotosArgs? = args?.photo
        if(currentPhoto != null) {
            photoViewModel.setPhoto(
                Photos(
                    currentPhoto.albumId,
                    currentPhoto.id,
                    currentPhoto.title,
                    currentPhoto.url,
                    currentPhoto.thumbnailUrl
                )
            )
        }


        photoViewModel.photo.observeForever {
            binding.imageView2.load(it.thumbnailUrl)
            binding.textView.text = it.id.toString()
            binding.textView2.text = it.albumId.toString()
            binding.textView3.text = it.title
        }

        return binding.root
    }
}