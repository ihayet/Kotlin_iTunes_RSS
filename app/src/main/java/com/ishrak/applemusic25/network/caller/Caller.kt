package com.ishrak.applemusic25.network.caller

import com.ishrak.applemusic25.data.DataAlbums25
import com.ishrak.applemusic25.network.builder.Builder
import com.ishrak.applemusic25.network.interfaces.ServiceAlbums25

import io.reactivex.rxjava3.core.Observable

class Caller {
    companion object {
        fun getTop25Albums(limit: Int): Observable<DataAlbums25> {
            val serviceAlbums25: ServiceAlbums25 = Builder.getBuilder().create(ServiceAlbums25::class.java)

            return serviceAlbums25.getTop25AlbumsService("top-albums",
                "all", limit, "explicit.json")
        }
    }
}