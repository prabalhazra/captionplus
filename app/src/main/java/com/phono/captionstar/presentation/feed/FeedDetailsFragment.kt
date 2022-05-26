package com.phono.captionstar.presentation.feed

import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.google.android.material.elevation.ElevationOverlayProvider
import com.phono.captionstar.databinding.FragmentFeedDetailsBinding
import com.phono.captionstar.presentation.adapter.FeedDetailsAdapter

class FeedDetailsFragment : Fragment() {

    private var _binding: FragmentFeedDetailsBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: FeedDetailsAdapter
    private val args: FeedDetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFeedDetailsBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.toolBar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }

        binding.collapsingToolBar.title = args.feed.title

        Glide.with(requireContext()).load(args.feed.url).into(binding.image)

        adapter = FeedDetailsAdapter(args.feed)
        binding.recyclerView.adapter = adapter

        //change the elevation of dark theme
        if (isDarkTheme(requireActivity())) {
            binding.collapsingToolBar.setContentScrimColor(
                ElevationOverlayProvider(requireActivity())
                    .compositeOverlayWithThemeSurfaceColorIfNeeded(4f)
            )
        }
    }

    //check if theme is dark mode or not
    private fun isDarkTheme(context: Context): Boolean {
        return context.resources.configuration.uiMode and
                Configuration.UI_MODE_NIGHT_MASK == Configuration.UI_MODE_NIGHT_YES
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}