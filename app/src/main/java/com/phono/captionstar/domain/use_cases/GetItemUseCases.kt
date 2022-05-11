package com.phono.captionstar.domain.use_cases

import com.phono.captionstar.domain.repository.CaptionRepository
import javax.inject.Inject

class GetItemUseCases @Inject constructor(
    private val repository: CaptionRepository
) {
    operator fun invoke(id: String) = repository.getItemDto(id)
}