package com.example.musicplayer

import android.app.Application
import android.content.Context
import com.example.musicplayer.models.MusicFile

class AppController : Application() {

    companion object {
        lateinit var context : Context
        var musicList = mutableListOf<MusicFile>()
    }

    override fun onCreate() {
        super.onCreate()
        context = this
    }
}