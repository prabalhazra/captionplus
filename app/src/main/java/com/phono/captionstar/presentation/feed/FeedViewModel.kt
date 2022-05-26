package com.phono.captionstar.presentation.feed

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.phono.captionstar.common.Response
import com.phono.captionstar.data.remote.dto.FeedDto
import com.phono.captionstar.domain.use_cases.GetFeedsUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FeedViewModel @Inject constructor(
    private val feedsUseCases: GetFeedsUseCases
) : ViewModel() {

    private val _state = MutableStateFlow<Response<List<FeedDto>>>(Response.Loading)
    val state: StateFlow<Response<List<FeedDto>>> = _state

    init {
        getFeedItems()
    }

    private fun getFeedItems() {
        viewModelScope.launch {
            feedsUseCases().collect {
                _state.value = it
            }
        }
    }
}