package com.phono.captionstar.presentation.details

import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.coroutineScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.google.android.material.elevation.ElevationOverlayProvider
import com.phono.captionstar.common.Response
import com.phono.captionstar.databinding.FragmentDetailsBinding
import com.phono.captionstar.presentation.adapter.DetailsAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class DetailsFragment : Fragment() {

    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!
    private val args: DetailsFragmentArgs by navArgs()
    private val viewModel: DetailsViewModel by viewModels()
    private lateinit var adapter: DetailsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailsBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getDetails(group = args.group, id = args.id, name = args.name)

        Glide.with(requireActivity()).load(args.url).into(binding.image)

        binding.collapsingToolBar.title = args.name

        getDetails()
        binding.toolBar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }

        //change the elevation of dark theme
        if (isDarkTheme(requireActivity())) {
            binding.collapsingToolBar.setContentScrimColor(
                ElevationOverlayProvider(requireActivity())
                    .compositeOverlayWithThemeSurfaceColorIfNeeded(4f)
            )
        }
    }

    private fun getDetails() {
        lifecycle.coroutineScope.launchWhenCreated {
            viewModel.state.collect { result ->
                when (result) {
                    is Response.Loading -> {
                        binding.progressCircular.visibility = View.VISIBLE
                        binding.detailsRecyclerView.visibility = View.GONE
                    }
                    is Response.Error -> {

                    }
                    is Response.Success -> {
                        adapter = DetailsAdapter(requireContext(),result.data)
                        binding.detailsRecyclerView.adapter = adapter
                        binding.progressCircular.visibility = View.GONE
                        binding.detailsRecyclerView.visibility = View.VISIBLE
                        Log.d("hello", result.data.toString())
                        Log.d("hello", "${args.group} , ${args.id} , ${args.name}")
                    }
                }
            }
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
