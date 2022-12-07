package com.deniz.watchwithme

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.deniz.watchwithme.databinding.ActivityLinkodasiBinding
import com.google.android.youtube.player.YouTubePlayerView
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener


class WatchRoom : AppCompatActivity() {


private lateinit var youTubePlayerView : YouTubePlayerView
private lateinit var binding : ActivityLinkodasiBinding


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_watch_room)
        youTubePlayerView = findViewById(R.id.ytb)
        val youTubePlayerView = findViewById<com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView>(
                R.id.ytb
            )
        lifecycle.addObserver(youTubePlayerView)

        youTubePlayerView.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
            override fun onReady(youTubePlayer: YouTubePlayer) {
                val videoId = "zBdvaeqDbto"
                youTubePlayer.cueVideo(videoId, 0F);
            }
        })



    }
}