package com.example.yogamat.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.yogamat.R
import com.example.yogamat.databinding.FragmentCreateYogaDialogueBinding
import com.example.yogamat.model.MyYoga
import com.example.yogamat.viewmodel.MyYogaListViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.coroutines.launch
import java.util.*


class CreateYogaDialogueFragment : BottomSheetDialogFragment() {
    private var _binding: FragmentCreateYogaDialogueBinding? = null
    private val binding
        get() = checkNotNull(_binding) {
            throw IllegalStateException("View not bound")
        }
    private val viewModel: MyYogaListViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentCreateYogaDialogueBinding.inflate(layoutInflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.submitButton.setOnClickListener {

            viewLifecycleOwner.lifecycleScope.launch {

                viewModel.yogaRepo.save(
                    MyYoga(
                        UUID.randomUUID(),
                        binding.createTitle.text.toString(),
                        binding.createDetails.text.toString(),
                        requireContext().resources.getStringArray(R.array.yoga_images)[0]
                    )
                )
            }

            findNavController().navigate(CreateYogaDialogueFragmentDirections.actionCreateYogaDialogueFragmentToMyYogaListFragment())
        }



    }



    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}