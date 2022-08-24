package com.khurram.test.domain.di


import android.content.Context
import androidx.room.Room
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.khurram.test.data.db.ContactDatabase
import com.khurram.test.domain.repository.DatabaseRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
object AppModule {


    @Provides
    @Singleton
    fun providesGsonBuilder(): Gson {
        return GsonBuilder().create()
    }

    @Provides
    @Singleton
    fun providesDatabaseRepository(database: ContactDatabase): DatabaseRepository {
        return DatabaseRepository(database)
    }

    @Provides
    @Singleton
    fun provideContactDatabase(@ApplicationContext context: Context) = Room.databaseBuilder(
        context.applicationContext,
        ContactDatabase::class.java,
        "contact_db")
        .fallbackToDestructiveMigration()
        .build()
}


