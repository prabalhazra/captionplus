package com.phono.captionstar.presentation.search

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.coroutineScope
import androidx.navigation.fragment.findNavController
import com.phono.captionstar.common.Response
import com.phono.captionstar.databinding.FragmentSearchBinding
import com.phono.captionstar.presentation.adapter.DetailsAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SearchFragment : Fragment() {

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!
    private val searchViewModel: SearchViewModel by viewModels()
    private lateinit var adapter: DetailsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.toolBar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }
        getData()
    }

    private fun getData() {
        binding.searchEdt.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(search: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (search.toString().isNotEmpty()) {
                    searchViewModel.getSearchData(binding.searchEdt.text.toString())
                    bindRecyclerView()
                }
            }

            override fun afterTextChanged(p0: Editable?) {}
        })
    }

    private fun bindRecyclerView() {
        lifecycle.coroutineScope.launch {
            searchViewModel.state.collect { result ->
                when(result) {
                    is Response.Loading -> {

                    }
                    is Response.Error -> {

                    }
                    is Response.Success -> {
                        adapter = DetailsAdapter(requireContext(),result.data)
                        binding.searchRecyclerView.adapter = adapter
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