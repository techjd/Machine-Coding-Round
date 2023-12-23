package com.techjd.moviedetails.di

import android.content.Context
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.techjd.moviedetails.R
import com.techjd.moviedetails.data.api.MovieApiInterface
import com.techjd.moviedetails.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

  @Provides
  @Singleton
  fun providesRetrofit(): Retrofit {
    return Retrofit.Builder()
      .baseUrl(Constants.BASE_URL)
      .addConverterFactory(GsonConverterFactory.create())
      .build()
  }

  @Provides
  @Singleton
  fun providesMovieApi(retrofit: Retrofit): MovieApiInterface {
    return retrofit.create(MovieApiInterface::class.java)
  }
}