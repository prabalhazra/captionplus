package com.phono.captionstar.presentation.hashtags

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.phono.captionstar.common.Response
import com.phono.captionstar.data.remote.dto.HashtagsDto
import com.phono.captionstar.domain.use_cases.GetHashtagsUseCases
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class HashtagsViewModel @Inject constructor(
    private val getHashtagsUseCases: GetHashtagsUseCases
) : ViewModel() {

    private val _state = MutableStateFlow<Response<List<HashtagsDto>>>(Response.Loading)
    val state: StateFlow<Response<List<HashtagsDto>>> = _state

    private fun getHashtag(id: String) {
        viewModelScope.launch {
            getHashtagsUseCases(id).collect {
                _state.value = it
            }
        }
    }

}