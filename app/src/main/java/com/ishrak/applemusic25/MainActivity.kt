package com.ishrak.applemusic25

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.ishrak.applemusic25.adapter.AdapterAlbum25
import com.ishrak.applemusic25.data.DataAlbums25
import com.ishrak.applemusic25.data.Result
import com.ishrak.applemusic25.databinding.ActivityMainBinding

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

import com.ishrak.applemusic25.network.caller.Caller

class MainActivity : AppCompatActivity() {
    private lateinit var mainBinding: ActivityMainBinding

    private var spinnerIdx: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mainBinding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        setContentView(mainBinding.root)

        var array_spinner: MutableList<Int> = mutableListOf(10, 15, 25)

        val adapter: ArrayAdapter<Int> = ArrayAdapter<Int>(this, android.R.layout.simple_spinner_item, array_spinner)
        mainBinding.spinner.adapter = adapter
        mainBinding.spinner.setSelection(2)
        mainBinding.spinner?.onItemSelectedListener = object: AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                Log.v("SPIN", array_spinner.get(position).toString())
                loadAlbums(array_spinner.get(position))
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }

        val linearLayoutManager: LinearLayoutManager = LinearLayoutManager(this)
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL

        mainBinding.recyclerView.layoutManager = LinearLayoutManager(this)
        mainBinding.recyclerView.adapter = AdapterAlbum25(ArrayList<Result>())
    }

    private fun loadAlbums(limit: Int){
        Caller.getTop25Albums(limit)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.newThread())
            .subscribe(
                {
                    Log.v("Response", it.toString())

                    val adapterAlbum25: AdapterAlbum25 = AdapterAlbum25(it.feed!!.results)

                    mainBinding.recyclerView.adapter = adapterAlbum25
                    adapterAlbum25.notifyDataSetChanged()
                },
                {
                    Log.e("Error", "Could not fetch top 25 albums!")
                })
    }
}