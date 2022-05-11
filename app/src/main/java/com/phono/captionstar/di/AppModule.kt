package com.phono.captionstar.di

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.phono.captionstar.data.repository.CaptionRepositoryImpl
import com.phono.captionstar.domain.repository.CaptionRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
@ExperimentalCoroutinesApi
object AppModule {

    @Provides
    @Singleton
    fun provideFirebaseFireStore() : FirebaseFirestore = Firebase.firestore

    @Singleton
    @Provides
    fun provideCaptionRepository(fireStore: FirebaseFirestore): CaptionRepository =
        CaptionRepositoryImpl(fireStore)

}