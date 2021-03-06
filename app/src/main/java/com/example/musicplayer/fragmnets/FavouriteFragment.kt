package com.builders.musicplayer.fragmnets

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.builders.musicplayer.AppController
import com.builders.musicplayer.R
import com.builders.musicplayer.adapters.MusicAdapter
import com.builders.musicplayer.database.FavSongDatabase
import com.builders.musicplayer.database.FavSongModel
import com.builders.musicplayer.models.MusicFile
import kotlinx.android.synthetic.main.fragment_home.*


class FavouriteFragment : Fragment() {

    private val TAG = "FavouriteFragment"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_favourite, container, false)
    }

    override fun onResume() {
        super.onResume()
        getFavList()
        initList()
    }

    private fun getFavList() {
        var filteredList = mutableListOf<MusicFile>()
        var favMusicList = activity?.let { FavSongDatabase.getInstance(it.baseContext).songDao().getAll() }
        var allMusicFile = AppController.musicList

        allMusicFile.forEach {
            var singleMusic = it
            favMusicList?.forEach {
                if (it.songId == singleMusic.id) {
                    filteredList.add(singleMusic)
                }
            }
        }

        AppController.setMusicForFavourite(filteredList as ArrayList<MusicFile>)
    }

    private fun initList() {
        var musicAdapter = MusicAdapter(AppController.musicList, context)
        recyclerview.setHasFixedSize(true)
        recyclerview.layoutManager = LinearLayoutManager(context)
        recyclerview.adapter = musicAdapter
    }

}