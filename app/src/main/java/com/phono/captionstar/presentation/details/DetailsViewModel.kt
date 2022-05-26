package com.phono.captionstar.presentation.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.phono.captionstar.common.Response
import com.phono.captionstar.data.remote.dto.DetailsDto
import com.phono.captionstar.data.remote.dto.HomeDto
import com.phono.captionstar.domain.use_cases.GetDetailsUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val detailsUseCases: GetDetailsUseCases
) : ViewModel() {

    private val _state = MutableStateFlow<Response<List<DetailsDto>>>(Response.Loading)
    val state: StateFlow<Response<List<DetailsDto>>> = _state

    fun getDetails(group: String, id: String, name: String) {
        viewModelScope.launch {
            detailsUseCases(
                group = group,
                id = id,
                name = name
            ).collect {
                _state.value = it
            }
        }
    }

}
