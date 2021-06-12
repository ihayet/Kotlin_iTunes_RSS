package com.ishrak.applemusic25.network.interfaces

import com.ishrak.applemusic25.data.DataAlbums25

import retrofit2.http.GET
import retrofit2.http.Path

import io.reactivex.rxjava3.core.Observable

interface ServiceAlbums25 {
    @GET("/api/v1/us/apple-music/{media_type}/{genre}/{results_limit}/{format}")
    fun getTop25AlbumsService(@Path("media_type") media_type: String,
                              @Path("genre") genre: String,
                              @Path("results_limit") results_limit: Int,
                              @Path("format") format: String): Observable<DataAlbums25>
}