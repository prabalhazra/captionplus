package com.phono.captionstar.presentation.feed

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.coroutineScope
import com.phono.captionstar.common.Response
import com.phono.captionstar.databinding.FragmentFeedBinding
import com.phono.captionstar.presentation.adapter.FeedAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class FeedFragment : Fragment() {

    private var _binding: FragmentFeedBinding? = null
    private val binding get() = _binding!!
    private lateinit var feedAdapter: FeedAdapter
    private val feedViewModel: FeedViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFeedBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getFeeds()
    }

    private fun getFeeds() {
        lifecycle.coroutineScope.launchWhenCreated {
            feedViewModel.state.collect { result ->
                when (result) {
                    is Response.Loading -> {
                        binding.progressCircular.visibility = View.VISIBLE
                        binding.feedRecyclerView.visibility = View.GONE
                    }
                    is Response.Error -> {

                    }
                    is Response.Success -> {
                        feedAdapter = FeedAdapter(requireContext(), result.data)
                        binding.progressCircular.visibility = View.GONE
                        binding.feedRecyclerView.visibility = View.VISIBLE
                        binding.feedRecyclerView.adapter = feedAdapter
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