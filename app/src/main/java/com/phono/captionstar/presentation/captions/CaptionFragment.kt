package com.phono.captionstar.presentation.captions

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.phono.captionstar.common.Response
import com.phono.captionstar.databinding.FragmentCaptionBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CaptionFragment : Fragment() {

    private var _binding: FragmentCaptionBinding? = null
    private val binding get() = _binding!!
    private val captionsViewModel : CaptionsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCaptionBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        when(captionsViewModel.state.value) {
            is Response.Loading -> {

            }
            is Response.Success -> {

            }
            is Response.Error -> {

            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}