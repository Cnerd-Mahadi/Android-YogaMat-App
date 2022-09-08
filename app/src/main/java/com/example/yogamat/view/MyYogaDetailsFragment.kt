package com.example.yogamat.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.navArgs
import com.example.yogamat.databinding.FragmentYogaDetailsBinding
import com.example.yogamat.viewmodel.MyYogaDetailsViewModel
import com.example.yogamat.viewmodel.MyYogaDetailsViewModelFactory
import com.squareup.picasso.Picasso
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MyYogaDetailsFragment : Fragment() {

    private var _binding: FragmentYogaDetailsBinding? = null
    private val binding
        get() = checkNotNull(_binding) {
            throw IllegalStateException("View not bound")
        }
    private val args: MyYogaDetailsFragmentArgs by navArgs()
    private val viewModel: MyYogaDetailsViewModel by viewModels {
        MyYogaDetailsViewModelFactory(args.yogaId)
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

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.yoga.collect{ data ->

                    data?.let { currentYoga ->
                        binding.yogaDetailsTitle.text = currentYoga.title
                        binding.yogaDetailsDetailsText.text = currentYoga.details
                        val imgResId = checkNotNull(requireContext().
                        resources.getIdentifier(currentYoga.image, "drawable", requireContext().packageName))
                        Picasso.get().load(imgResId).resize(400, 350).into(binding.yogaDetailsImageview)
                    }

                }
            }
        }


    }



    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}