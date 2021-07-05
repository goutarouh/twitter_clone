package com.morningnightdream.clone_twitter.di

import com.morningnightdream.clone_twitter.ui.home.feeds.data.TweetsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideTweetsRepository(): TweetsRepository {
        return TweetsRepository()
    }

}