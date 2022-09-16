package com.example.yogamat.view

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.FileProvider
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.yogamat.databinding.FragmentCreateYogaDialogueBinding
import com.example.yogamat.model.MyYoga
import com.example.yogamat.model.YogaDatabase
import com.example.yogamat.model.YogaRepo
import com.example.yogamat.viewmodel.MyYogaListViewModel
import com.example.yogamat.viewmodel.MyYogaListViewModelFactory
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.coroutines.launch
import java.util.*


class CreateYogaDialogueFragment : BottomSheetDialogFragment() {
    private var _binding: FragmentCreateYogaDialogueBinding? = null
    private val binding
        get() = checkNotNull(_binding) {
            throw IllegalStateException("View not bound")
        }
    private val viewModel: MyYogaListViewModel by viewModels(){
        MyYogaListViewModelFactory(requireActivity().application)
    }
    private lateinit var newYoga: MyYoga
    private lateinit var photoURI: Uri

    private val imageCaptureResultRegister = registerForActivityResult(ActivityResultContracts.TakePicture()) { success ->
            if(success) {
                binding.yogaImageView.setImageURI(null)
                binding.yogaImageView.setImageURI(photoURI)
            }
    }


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

        newYoga = MyYoga(UUID.randomUUID())

        binding.submitButton.setOnClickListener {
            viewLifecycleOwner.lifecycleScope.launch {
                newYoga.title = binding.createTitle.text.toString()
                newYoga.details = binding.createDetails.text.toString()
                viewModel.yogaRepo.save(newYoga)
            }

            findNavController().navigate(CreateYogaDialogueFragmentDirections.actionCreateYogaDialogueFragmentToMyYogaListFragment())
        }

        binding.capturePhoto.setOnClickListener {
            photoURI = YogaRepo(YogaDatabase.getDB(), requireContext().applicationContext).getImageUri(newYoga)

            imageCaptureResultRegister.launch(photoURI)

        }





    }



    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}