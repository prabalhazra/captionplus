package com.phono.captionstar.presentation.captions

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.phono.captionstar.common.Response
import com.phono.captionstar.data.remote.dto.CaptionDto
import com.phono.captionstar.domain.use_cases.GetCaptionsUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CaptionsViewModel @Inject constructor(
    private val captionsUseCases: GetCaptionsUseCases
) : ViewModel() {
    private val _state = MutableStateFlow<Response<List<CaptionDto>>>(Response.Loading)
    val state: StateFlow<Response<List<CaptionDto>>> = _state

    fun getCaptions(id: String) {
        viewModelScope.launch {
            captionsUseCases(id).collect {
                _state.value = it
            }
        }
    }
}