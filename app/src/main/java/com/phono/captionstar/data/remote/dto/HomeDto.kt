package com.phono.captionstar.data.remote.dto

data class HomeDto(
    val id : String? = "",
    val name: String? = "",
    val items: List<ItemDto>? = emptyList()
)
