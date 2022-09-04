package com.example.yogamat.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.yogamat.databinding.FragmentMyYogaHostBinding


class MyYogaHostFragment : Fragment() {
    private var _binding: FragmentMyYogaHostBinding? = null
    private val binding
        get() = checkNotNull(_binding) {
            throw IllegalStateException("View not bound")
        }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentMyYogaHostBinding.inflate(layoutInflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)





    }





    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}