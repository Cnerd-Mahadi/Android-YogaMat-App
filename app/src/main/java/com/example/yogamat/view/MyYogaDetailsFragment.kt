package com.example.yogamat.view

import android.app.Application
import android.content.ClipData
import android.content.Context
import android.graphics.ColorSpace
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.yogamat.R
import com.example.yogamat.databinding.FragmentYogaDetailsBinding
import com.example.yogamat.databinding.FragmentYogaListBinding
import com.example.yogamat.model.Yoga
import com.example.yogamat.viewmodel.MyYogaDetailsViewModel
import com.example.yogamat.viewmodel.YogaDetailsViewModel
import com.example.yogamat.viewmodel.YogaDetailsViewModelFactory
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.squareup.picasso.Picasso
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.lang.reflect.Type
import java.util.*
import kotlin.collections.ArrayList

class MyYogaDetailsFragment : Fragment() {

    private var _binding: FragmentYogaDetailsBinding? = null
    private val binding
        get() = checkNotNull(_binding) {
            throw IllegalStateException("View not bound")
        }
    private val args: YogaDetailsFragmentArgs by navArgs()
    private lateinit var application: Application
    private val viewModel: MyYogaDetailsViewModel by viewModels{
        YogaDetailsViewModelFactory(args.yogaId, application)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentYogaDetailsBinding.inflate(layoutInflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewLifecycleOwner.lifecycleScope.launch{
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModel.yoga.collect{ currentYoga ->
                    binding.yogaDetailsTitle.text = requireNotNull(currentYoga).title
                    binding.yogaDetailsDetailsText.text = currentYoga.details
                    val imgResId = checkNotNull(requireContext().
                    resources.getIdentifier(currentYoga.image, "drawable", requireContext().packageName))
                    Picasso.get().load(imgResId).resize(400, 350).into(binding.yogaDetailsImageview)
                }
            }
        }
    }



    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}