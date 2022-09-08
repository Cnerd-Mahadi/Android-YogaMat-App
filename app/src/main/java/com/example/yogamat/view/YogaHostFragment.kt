package com.example.yogamat.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.yogamat.Event
import com.example.yogamat.R
import com.example.yogamat.databinding.FragmentYogaHostBinding
import com.example.yogamat.viewmodel.RouteUtilViewModel

class YogaHostFragment : Fragment() {
    private var _binding: FragmentYogaHostBinding? = null
    private val binding
        get() = checkNotNull(_binding) {
            throw IllegalStateException("View not bound")
        }
    private val routeUtilViewModel by activityViewModels<RouteUtilViewModel>()
    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentYogaHostBinding.inflate(layoutInflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = (childFragmentManager.findFragmentById(R.id.parent_container) as NavHostFragment).navController

    }

    override fun onResume() {
        super.onResume()
        routeUtilViewModel.currentNavController.value = Event(navController)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}