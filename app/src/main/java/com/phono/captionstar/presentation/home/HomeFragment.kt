package com.phono.captionstar.presentation.home

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.coroutineScope
import com.phono.captionstar.common.Response
import com.phono.captionstar.databinding.FragmentHomeBinding
import com.phono.captionstar.presentation.adapter.HomeAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val homeViewModel: HomeViewModel by viewModels()
    private lateinit var adapter: HomeAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getCaptionsDetails()
    }
    override fun onResume() {
        super.onResume()
        Log.d("hello", "resume")
    }


    private fun getCaptionsDetails() {
        lifecycle.coroutineScope.launchWhenCreated {
            homeViewModel.captionState.collect { result ->
                when (result) {
                    is Response.Loading -> {
                        binding.progressCircular.visibility = View.VISIBLE
                        binding.captionsRecyclerView.visibility = View.GONE
                    }
                    is Response.Error -> {
                        Log.e("hello", result.message)
                    }
                    is Response.Success -> {
                        binding.progressCircular.visibility = View.GONE
                        binding.captionsRecyclerView.visibility = View.VISIBLE
                        adapter = HomeAdapter(requireContext(), result.data)
                        binding.captionsRecyclerView.adapter = adapter
                        Log.d("hello", result.data.toString())
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}