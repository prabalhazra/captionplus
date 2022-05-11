package com.phono.captionstar.data.repository

import com.google.firebase.firestore.FirebaseFirestore
import com.phono.captionstar.common.Constants
import com.phono.captionstar.common.Response
import com.phono.captionstar.data.remote.dto.CaptionDto
import com.phono.captionstar.data.remote.dto.HashtagsDto
import com.phono.captionstar.data.remote.dto.HomeDto
import com.phono.captionstar.data.remote.dto.ItemDto
import com.phono.captionstar.domain.repository.CaptionRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

@ExperimentalCoroutinesApi
class CaptionRepositoryImpl @Inject constructor(
    private val firebaseFirestore: FirebaseFirestore
) : CaptionRepository {
    override fun getCaptions(id: String): Flow<Response<List<CaptionDto>>> = callbackFlow {
        val snapShotListener = firebaseFirestore
            .collection(Constants.CAPTIONS)
            .document(id)
            .collection(Constants.CAPTION)
            .addSnapshotListener { snapShot, e ->
                val response = if (snapShot != null) {
                    val captions = snapShot.toObjects(CaptionDto::class.java)
                    Response.Success(captions)
                } else {
                    Response.Error(e?.message ?: e.toString())
                }
                trySend(response).isSuccess
            }
        awaitClose {
            snapShotListener.remove()
        }
    }

    override fun getHashtags(id: String): Flow<Response<List<HashtagsDto>>> = callbackFlow {
        val snapShotListener = firebaseFirestore
            .collection(Constants.HASHTAGS)
            .document(id)
            .collection(Constants.HASHTAGS)
            .addSnapshotListener { snapShot, e ->
                val response = if (snapShot != null) {
                    val captions = snapShot.toObjects(HashtagsDto::class.java)
                    Response.Success(captions)
                } else {
                    Response.Error(e?.message ?: e.toString())
                }
                trySend(response).isSuccess
            }
        awaitClose {
            snapShotListener.remove()
        }
    }

    override fun getCaptionHomeDto(): Flow<Response<List<HomeDto>>> = callbackFlow {
        val snapShotListener = firebaseFirestore
            .collection(Constants.CAPTIONS)
            .addSnapshotListener { snapShot, e ->
                val response = if (snapShot != null) {
                    val captions = snapShot.toObjects(HomeDto::class.java)
                    Response.Success(captions)
                } else {
                    Response.Error(e?.message ?: e.toString())
                }
                trySend(response).isSuccess
            }
        awaitClose {
            snapShotListener.remove()
        }
    }

    override fun getItemDto(id: String): Flow<Response<List<ItemDto>>> = callbackFlow {
        val snapShotListener = firebaseFirestore
            .collection(Constants.CAPTIONS)
            .document(id)
            .collection(Constants.CAPTION)
            .addSnapshotListener { snapShot, e ->
                val response = if (snapShot != null) {
                    val captions = snapShot.toObjects(ItemDto::class.java)
                    Response.Success(captions)
                } else {
                    Response.Error(e?.message ?: e.toString())
                }
                trySend(response).isSuccess
            }
        awaitClose {
            snapShotListener.remove()
        }
    }

    override fun getHashtagDto(): Flow<Response<List<ItemDto>>> = callbackFlow {
        val snapShotListener = firebaseFirestore
            .collection(Constants.HASHTAGS)
            .addSnapshotListener { snapShot, e ->
                val response = if (snapShot != null) {
                    val captions = snapShot.toObjects(ItemDto::class.java)
                    Response.Success(captions)
                } else {
                    Response.Error(e?.message ?: e.toString())
                }
                trySend(response).isSuccess
            }
        awaitClose {
            snapShotListener.remove()
        }
    }
}