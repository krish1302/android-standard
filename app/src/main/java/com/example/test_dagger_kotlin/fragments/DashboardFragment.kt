package com.example.test_dagger_kotlin.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.room.RoomDatabase
import com.example.test_dagger_kotlin.R
import com.example.test_dagger_kotlin.databinding.FragmentDashboardBinding
import com.example.test_dagger_kotlin.viewmodel.DashboardViewModel
import com.example.test_dagger_kotlin.viewmodelfactory.DashboardViewModelFactory
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class DashboardFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    @Inject
    lateinit var dashboardViewModelFactory: DashboardViewModelFactory

    private val dashboardViewModel: DashboardViewModel by viewModels {
        dashboardViewModelFactory
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textDashboard
        dashboardViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }

        dashboardViewModel.getUsers()

        dashboardViewModel.getUser.observeForever {
            if (it.isNotEmpty()){
                Toast.makeText(requireContext(), it[0].firstName, Toast.LENGTH_SHORT).show()
            }
        }

        binding.button.setOnClickListener {
            findNavController().navigate(R.id.action_navigation_dashboard_to_navigation_notifications)
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}