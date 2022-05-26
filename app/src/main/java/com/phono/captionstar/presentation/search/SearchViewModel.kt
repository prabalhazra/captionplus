package com.phono.captionstar.presentation.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.phono.captionstar.common.Response
import com.phono.captionstar.data.remote.dto.DetailsDto
import com.phono.captionstar.domain.use_cases.GetSearchUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchUseCases: GetSearchUseCases
) : ViewModel() {

    private val _state = MutableStateFlow<Response<List<DetailsDto>>>(Response.Loading)
    val state: StateFlow<Response<List<DetailsDto>>> = _state

    fun getSearchData(query: String) {
        viewModelScope.launch {
            searchUseCases(query).collect {
                _state.value = it
            }
        }
    }
}