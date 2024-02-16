package com.sudhanshu.quizapp.di

import androidx.compose.ui.text.googlefonts.GoogleFont
import com.google.ai.client.generativeai.GenerativeModel
import com.sudhanshu.quizapp.BuildConfig
import com.sudhanshu.quizapp.R
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providesGenerativeAIInstance(): GenerativeModel{
        return GenerativeModel(
            modelName = "gemini-pro",
            apiKey = BuildConfig.API_KEY
        )
    }
}