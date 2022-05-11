package com.phono.captionstar.domain.repository

import com.phono.captionstar.common.Response
import com.phono.captionstar.data.remote.dto.CaptionDto
import com.phono.captionstar.data.remote.dto.HashtagsDto
import com.phono.captionstar.data.remote.dto.HomeDto
import com.phono.captionstar.data.remote.dto.ItemDto
import kotlinx.coroutines.flow.Flow

interface CaptionRepository {

    fun getCaptions(id: String): Flow<Response<List<CaptionDto>>>

    fun getHashtags(id: String): Flow<Response<List<HashtagsDto>>>

    fun getCaptionHomeDto(): Flow<Response<List<HomeDto>>>

    fun getItemDto(id: String): Flow<Response<List<ItemDto>>>

    fun getHashtagDto(): Flow<Response<List<ItemDto>>>
}