package com.ishrak.applemusic25.adapter

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.ishrak.applemusic25.AlbumDetails
import com.ishrak.applemusic25.R
import com.ishrak.applemusic25.data.Result
import com.ishrak.applemusic25.databinding.RecyclerviewRowBinding

class AdapterAlbum25(private val dataset: ArrayList<Result>): RecyclerView.Adapter<AdapterAlbum25.ViewHolder>() {
    private lateinit var rowBinding: RecyclerviewRowBinding

    inner class ViewHolder(binding: RecyclerviewRowBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(viewGroup: ViewGroup, pos: Int): ViewHolder {
        rowBinding = RecyclerviewRowBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)

        return ViewHolder(rowBinding)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, pos: Int) {
        Glide.with(rowBinding.root)
            .load(dataset[pos].artworkUrl100)
            .into(rowBinding.albumImageView)

        rowBinding.albumNameTextView.text = dataset[pos].name
        rowBinding.artistNameTextView.text = dataset[pos].artistName

        rowBinding.root.setOnClickListener { view ->
            var intent: Intent = Intent(view.context, AlbumDetails::class.java).apply {
                var genres = ""

                for(items in dataset[pos].genres){
                    genres = genres.plus(items.name)

                    if(items != dataset[pos].genres.last()){
                        genres = genres.plus(", ")
                    }
                }

                putExtra("name", dataset[pos].name)
                putExtra("artistName", dataset[pos].artistName)
                putExtra("albumImage", dataset[pos].artworkUrl100)
                putExtra("release", dataset[pos].releaseDate)
                putExtra("genres", genres)
                putExtra("advisory", dataset[pos].contentAdvisoryRating)
                putExtra("copyright", dataset[pos].copyright)
            }

            view.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = dataset.size

    override fun getItemId(position: Int): Long = position as Long

    override fun getItemViewType(position: Int): Int = position
}