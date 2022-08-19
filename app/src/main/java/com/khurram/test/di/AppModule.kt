package com.khurram.test.di


import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.khurram.test.data.network.APIsInterface
import com.khurram.test.data.repository.network.NetworkRepository
import com.khurram.test.data.repository.network.NetworkRepositoryImpl
import com.khurram.test.util.Constants
import com.khurram.test.util.NetworkStatusTracker
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
object AppModule {


    private val BASE_URL= Constants.BASE_URL

    private val interceptor: HttpLoggingInterceptor = HttpLoggingInterceptor().setLevel(
        HttpLoggingInterceptor.Level.BODY
    )

    private val okHttpClient = OkHttpClient.Builder()
        .readTimeout(160, TimeUnit.SECONDS)
        .connectTimeout(160, TimeUnit.SECONDS)
        .addInterceptor(interceptor)
        .build()

    @Provides
    @Singleton
    fun providesGsonBuilder(): Gson {
        return GsonBuilder().create()
    }

    @Provides
    @Singleton
    fun providesRetrofit(gson: Gson): Retrofit.Builder {
            return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
    }

    @Provides
    @Singleton
    fun providesNetworkAPI(retrofit: Retrofit.Builder): APIsInterface {
        return retrofit.build().create(APIsInterface::class.java)
    }

    @Provides
    @Singleton
    fun providesNetworkRepository(apIsInterface: APIsInterface): NetworkRepository {
        return NetworkRepositoryImpl(apIsInterface)
    }

    @Provides
    @Singleton
    fun providesNetworkStatusTracker(@ApplicationContext context: Context): NetworkStatusTracker {
        return NetworkStatusTracker(context)
    }

}


