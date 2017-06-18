package com.blacklenspub.postsreader.util

import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class UtilModule {

    @Provides @Singleton
    fun provideLogger(): Logger = LoggerImpl()
}

