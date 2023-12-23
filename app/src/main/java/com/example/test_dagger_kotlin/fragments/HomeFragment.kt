package com.example.test_dagger_kotlin.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.test_dagger_kotlin.adapter.PhotosAdapter
import com.example.test_dagger_kotlin.databinding.FragmentHomeBinding
import com.example.test_dagger_kotlin.fragments.HomeFragmentDirections
import com.example.test_dagger_kotlin.interfaces.RecyclerViewEvent
import com.example.test_dagger_kotlin.models.Photos
import com.example.test_dagger_kotlin.navargsmodel.PhotosArgs
import com.example.test_dagger_kotlin.viewmodel.HomeViewModel
import com.example.test_dagger_kotlin.viewmodelfactory.HomeViewModelFactory
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : Fragment(), RecyclerViewEvent {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    @Inject
    lateinit var homeViewModelFactory: HomeViewModelFactory

    private lateinit var photosAdapter: PhotosAdapter

    private val homeViewModel: HomeViewModel by viewModels {
        homeViewModelFactory
    }


    @SuppressLint("NotifyDataSetChanged")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val linearLayoutManager = LinearLayoutManager(requireContext())
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        _binding?.photoRecyclerView?.layoutManager = linearLayoutManager
        _binding?.photoRecyclerView?.setHasFixedSize(false)
        photosAdapter = PhotosAdapter(this@HomeFragment)
        _binding?.photoRecyclerView?.adapter = photosAdapter

        // observe viewmodel data and update recycler view adapter
        homeViewModel.photos.observeForever {
            if(it.isNotEmpty()){
//                binding?.progressBar?.visibility = View.GONE
                photosAdapter = PhotosAdapter(this@HomeFragment)
                photosAdapter.updateData(it)
                _binding?.photoRecyclerView?.adapter = photosAdapter
            }
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onItemClick(position: Int, item: Photos) {
        val photosArgs: PhotosArgs = PhotosArgs(item.albumId, item.id, item.title, item.url, item.thumbnailUrl)
        val action = HomeFragmentDirections.actionNavigationHomeToPhotoFragment(photosArgs)
        findNavController().navigate(action)
    }
}