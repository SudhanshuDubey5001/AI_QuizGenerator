package com.sudhanshu.quizapp.di

import androidx.compose.ui.text.googlefonts.GoogleFont
import com.google.ai.client.generativeai.GenerativeModel
import com.sudhanshu.quizapp.BuildConfig
import com.sudhanshu.quizapp.R
import com.sudhanshu.quizapp.feature_quiz.data.repository.AI_OperationsImpl
import com.sudhanshu.quizapp.feature_quiz.domain.repository.AI_Operations
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
    @Provides
    @Singleton
    fun providesAIOperationInstance(generativeModel: GenerativeModel): AI_Operations{
        return AI_OperationsImpl(generativeModel)
    }
}