package com.ishrak.applemusic25.network.builder

import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import okhttp3.OkHttpClient

class Builder() {
    companion object {
        fun getBuilder(): Retrofit {
            return Retrofit.Builder()
                .baseUrl("https://rss.itunes.apple.com")
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(OkHttpClient.Builder().build())
                .build()
        }
    }
}