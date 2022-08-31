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
import com.example.yogamat.adapter.ListMyYogaAdapter
import com.example.yogamat.adapter.OnClickMyAction
import com.example.yogamat.databinding.FragmentMyYogaListBinding
import com.example.yogamat.viewmodel.MyYogaListViewModel
import kotlinx.coroutines.launch
import java.util.*

class MyYogaListFragment : Fragment(),OnClickMyAction {
    private var _binding: FragmentMyYogaListBinding? = null
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

        _binding = FragmentMyYogaListBinding.inflate(layoutInflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        viewLifecycleOwner.lifecycleScope.launch{
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.yogaList.collect{ currentList ->
                    binding.yogaRecyclerView.adapter = ListMyYogaAdapter(currentList, this@MyYogaListFragment, requireContext())
                }
            }
        }



    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onCLick(id: UUID) {

    }


}