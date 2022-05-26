package com.phono.captionstar.domain.repository

import com.phono.captionstar.common.Response
import com.phono.captionstar.data.remote.dto.*
import kotlinx.coroutines.flow.Flow

interface CaptionRepository {

    fun getCaptions(id: String): Flow<Response<List<CaptionDto>>>

    fun getHashtags(id: String): Flow<Response<List<HashtagsDto>>>

    fun getCaptionHomeDto(): Flow<Response<List<HomeDto>>>

    fun getItemDto(id: String): Flow<Response<List<ItemDto>>>

    fun getHashtagDto(): Flow<Response<List<ItemDto>>>

    fun getDetailsDto(group: String, id: String, name: String): Flow<Response<List<DetailsDto>>>

    fun getFeedDto() : Flow<Response<List<FeedDto>>>

    fun getSearchData(query: String) : Flow<Response<List<DetailsDto>>>
}