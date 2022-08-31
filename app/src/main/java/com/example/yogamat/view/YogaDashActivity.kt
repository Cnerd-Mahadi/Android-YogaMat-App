package com.example.yogamat.view

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.yogamat.R
import com.example.yogamat.adapter.PagerAdapter
import com.example.yogamat.databinding.ActivityYogaDashBinding
import com.example.yogamat.model.YogaDatabase
import com.google.android.material.tabs.TabLayoutMediator


class YogaDashActivity: AppCompatActivity() {

    private var _binding: ActivityYogaDashBinding? = null
    private val binding
    get() = checkNotNull(_binding) { throw IllegalStateException("View not bound") }
    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityYogaDashBinding.inflate(layoutInflater)
        setContentView(binding.root)
        YogaDatabase.invoke(this)
        navController = (supportFragmentManager.findFragmentById(R.id.yoga_fragment_container) as NavHostFragment).navController
        setNav(navController)
        //setTab()
        handleScreen(navController)


    }

//    private fun setTab() {
//        val adapter = PagerAdapter(supportFragmentManager, lifecycle)
//        binding.yogaPager.adapter = adapter
//        adapter.initiateFragments(YogaListFragment(),"YogaList")
//        adapter.initiateFragments(MyYogaListFragment(), "MyYoga")
//        TabLayoutMediator(binding.yogaTab, binding.yogaPager) { tab, position ->
//            tab.text = adapter.getTitle(position)
//        }.attach()
//    }

    private fun handleScreen(navController: NavController) {

        navController.addOnDestinationChangedListener{ _ , destination, _ ->
            if (destination.id == R.id.myYogaListFragment || destination.id == R.id.yogaListFragment)
                binding.yogaNavBar.visibility = View.VISIBLE
            else
                binding.yogaNavBar.visibility = View.GONE

        }

    }

    private fun setNav(navController: NavController) {
        binding.yogaNavBar.setupWithNavController(navController)
        NavigationUI.setupWithNavController(binding.yogaNavBar, navController)

    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}