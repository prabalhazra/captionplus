package com.phono.captionstar.domain.use_cases

import com.phono.captionstar.domain.repository.CaptionRepository
import javax.inject.Inject

class GetCaptionUseCases @Inject constructor(
    private val repository: CaptionRepository
) {
    operator fun invoke() = repository.getCaptionHomeDto()
}