package com.sahanmondal.mvvm_firebase_authentication.di

import com.sahanmondal.mvvm_firebase_authentication.repositories.AuthRepository
import com.sahanmondal.mvvm_firebase_authentication.repositories.DefaultAuthRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ActivityScoped
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object AuthModule {

    @Provides
    @ViewModelScoped
    fun provideAuthRepository() = DefaultAuthRepository() as AuthRepository
}