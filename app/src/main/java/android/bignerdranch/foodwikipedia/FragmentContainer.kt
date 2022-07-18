package android.bignerdranch.foodwikipedia

import android.bignerdranch.foodwikipedia.databinding.FragmentContainerBinding
import android.content.res.Resources
import android.media.MediaPlayer
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

class FragmentContainer: AppCompatActivity() {
    private lateinit var binding: FragmentContainerBinding
    //private lateinit var mediaPlayer: MediaPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = FragmentContainerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //mediaPlayer = MediaPlayer.create(this, R.raw.background_music)

        if (savedInstanceState == null)
            launchHeader()

    }

    private fun launchHeader() {
        supportFragmentManager.beginTransaction()
            .setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
            .replace(R.id.fragmentContainer, Header.newInstance())
            .commit()
    }


    override fun onStart() {
        super.onStart()
        //mediaPlayer.start()
    }

    override fun onStop() {
        super.onStop()
        //mediaPlayer.pause()
    }

    override fun onDestroy() {
        super.onDestroy()
        //mediaPlayer.stop()
        //mediaPlayer.release()
    }

}