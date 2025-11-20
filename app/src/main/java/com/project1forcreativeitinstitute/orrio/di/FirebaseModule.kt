package com.project1forcreativeitinstitute.orrio.di

import com.google.firebase.auth.FirebaseAuth
import com.project1forcreativeitinstitute.orrio.data.repository.AuthRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import jakarta.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class FirebaseModule {
    @Provides
    @Singleton
    fun providesFirebaseAuth(): FirebaseAuth {
        return FirebaseAuth.getInstance()
}
    @Provides
    @Singleton
    fun providesFirebase(jAuth: FirebaseAuth): AuthRepository {
        return AuthRepository(jAuth)
    }

}