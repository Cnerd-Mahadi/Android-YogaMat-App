package com.example.yogamat.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.viewpager2.widget.ViewPager2
import com.example.yogamat.adapter.PagerAdapter
import com.example.yogamat.databinding.FragmentViewPagerContainerBinding
import com.example.yogamat.viewmodel.RouteUtilViewModel
import com.google.android.material.tabs.TabLayoutMediator

class ViewPagerContainerFragment : Fragment() {
    private var _binding: FragmentViewPagerContainerBinding? = null
    private val binding
        get() = checkNotNull(_binding) {
            throw IllegalStateException("View not bound")
        }
    private val routeUtilViewModel by activityViewModels<RouteUtilViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentViewPagerContainerBinding.inflate(layoutInflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setTab()
        handleTab()

    }

    private fun handleTab() {

        routeUtilViewModel.tabVisibility.observe(viewLifecycleOwner) { visibility ->
            if (visibility)
                binding.yogaTab.visibility = View.VISIBLE
            else
                binding.yogaTab.visibility = View.GONE

        }

    }

    private fun setTab() {
        val adapter = PagerAdapter(childFragmentManager, viewLifecycleOwner.lifecycle)
        binding.yogaPager.adapter = adapter
        adapter.initiateFragments(YogaHostFragment(), "YogaList")
        adapter.initiateFragments(MyYogaHostFragment(), "MyYoga")
        TabLayoutMediator(binding.yogaTab, binding.yogaPager) { tab, position ->
            tab.text = adapter.getTitle(position)
        }.attach()
    }



    override fun onDestroy() {
        super.onDestroy()
        binding.yogaPager.adapter = null
        _binding = null
    }
}