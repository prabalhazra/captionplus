package com.phono.captionstar.data.remote.dto

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.parcelize.Parcelize

@Keep
@Parcelize
data class FeedDto(
    val id: String? = "",
    val title: String? = "",
    val url: String? = "",
    val date: String? = "",
    val content: String? = ""
) : Parcelable
