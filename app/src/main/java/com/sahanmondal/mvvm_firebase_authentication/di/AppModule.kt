package com.sahanmondal.mvvm_firebase_authentication.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@Module
@InstallIn(ViewModelComponent::class)
object AppModule {

    @Provides
    @ViewModelScoped
    fun provideApplicationContext(
        @ApplicationContext context: Context
    ) = context

    @Provides
    @ViewModelScoped
    fun provideMainDispatcher() = Dispatchers.Main as CoroutineDispatcher
}