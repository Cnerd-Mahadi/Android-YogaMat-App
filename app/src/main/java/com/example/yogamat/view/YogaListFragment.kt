package com.example.yogamat.view

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.yogamat.R
import com.example.yogamat.adapter.ListYogaAdapter
import com.example.yogamat.adapter.OnClickAction
import com.example.yogamat.adapter.PagerAdapter
import com.example.yogamat.databinding.FragmentYogaListBinding
import com.example.yogamat.model.Yoga
import com.example.yogamat.viewmodel.RouteUtilViewModel
import com.example.yogamat.viewmodel.YogaListViewModel
import com.google.android.material.tabs.TabLayoutMediator
import com.google.gson.Gson

class YogaListFragment : Fragment(), OnClickAction{

    private var _binding: FragmentYogaListBinding? = null
    private val binding
    get() = checkNotNull(_binding) {
        throw IllegalStateException("View not bound")
    }

    private val viewModel: YogaListViewModel by viewModels()
    private var yogaList = mutableListOf<Yoga>()
    private val routeUtilViewModel by activityViewModels<RouteUtilViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentYogaListBinding.inflate(layoutInflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.yogaRecyclerView.layoutManager = LinearLayoutManager(context)
        binding.yogaRecyclerView.adapter = ListYogaAdapter(viewModel.yogaList, this@YogaListFragment, requireContext())

    }

    override fun onResume() {
        super.onResume()
        routeUtilViewModel.tabVisibility.value = true
    }

    override fun onPause() {
        super.onPause()
        routeUtilViewModel.tabVisibility.value = false
    }



    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onCLick(id: Int) {

        findNavController().navigate(
            YogaListFragmentDirections.actionYogaListFragmentToYogaDetailsFragment(id)
        )


    }

}