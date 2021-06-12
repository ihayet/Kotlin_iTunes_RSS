package com.ishrak.applemusic25

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import com.bumptech.glide.Glide
import com.ishrak.applemusic25.databinding.ActivityAlbumDetailsBinding

class AlbumDetails : AppCompatActivity() {
    private lateinit var albumDetailsBinding: ActivityAlbumDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        albumDetailsBinding = ActivityAlbumDetailsBinding.inflate(LayoutInflater.from(this), null, false)
        setContentView(albumDetailsBinding.root)

        val name = intent.getStringExtra("name")
        val artistName = intent.getStringExtra("artistName")
        val albumImage = intent.getStringExtra("albumImage")
        val release = intent.getStringExtra("release")
        val genres = intent.getStringExtra("genres")
        val advisory = intent.getStringExtra("advisory")
        val copyright = intent.getStringExtra("copyright")

        Glide.with(albumDetailsBinding.root)
            .load(albumImage)
            .into(albumDetailsBinding.albumDetailsImageView)

        albumDetailsBinding.albumDetailsNameTextView.text = name
        albumDetailsBinding.albumDetailsArtistNameTextView.text = artistName
        albumDetailsBinding.albumDetailsReleaseDateTextView.text = release
        albumDetailsBinding.albumDetailsGenresTextView.text = genres
        albumDetailsBinding.albumDetailsAdvisoryTextView.text = advisory
        albumDetailsBinding.albumDetailsCopyrightTextView.text = copyright

        val data = intent.getSerializableExtra("data")

        Log.v("INT_DATA", data.toString())
    }
}