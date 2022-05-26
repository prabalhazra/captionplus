package com.phono.captionstar.domain.use_cases

import com.phono.captionstar.domain.repository.CaptionRepository
import javax.inject.Inject

class GetDetailsUseCases @Inject constructor(
    private val repository: CaptionRepository
) {
    operator fun invoke(group: String, id: String, name: String) =
        repository.getDetailsDto(group, id, name)
}