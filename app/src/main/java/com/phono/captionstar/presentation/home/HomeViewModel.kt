package com.phono.captionstar.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.phono.captionstar.common.Response
import com.phono.captionstar.data.remote.dto.HomeDto
import com.phono.captionstar.data.remote.dto.ItemDto
import com.phono.captionstar.domain.use_cases.GetCaptionUseCases
import com.phono.captionstar.domain.use_cases.GetHashtagUseCases
import com.phono.captionstar.domain.use_cases.GetItemUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getCaptionUseCases: GetCaptionUseCases,
    private val getHashtagUseCases: GetHashtagUseCases,
    private val getItemUseCases: GetItemUseCases
) : ViewModel() {

    private val _captionState = MutableStateFlow<Response<List<HomeDto>>>(Response.Loading)
    val captionState: StateFlow<Response<List<HomeDto>>> = _captionState

    private val _hashtagState = MutableStateFlow<Response<List<ItemDto>>>(Response.Loading)
    val hashtagState: StateFlow<Response<List<ItemDto>>> = _hashtagState

    private val _itemState = MutableStateFlow<Response<List<ItemDto>>>(Response.Loading)
    val itemState: StateFlow<Response<List<ItemDto>>> = _itemState

    init {
        getCaption()
        getHashtag()
    }

    private fun getItem(id: String) {
        viewModelScope.launch {
            getItemUseCases(id).collect {
                _itemState.value = it
            }
        }
    }

    private fun getHashtag() {
        viewModelScope.launch {
            getHashtagUseCases().collect {
                _hashtagState.value = it
            }
        }
    }

    private fun getCaption() {
        viewModelScope.launch {
            getCaptionUseCases().collect {
                _captionState.value = it
            }
        }
    }

}