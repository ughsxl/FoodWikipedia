package android.bignerdranch.foodwikipedia

import android.bignerdranch.foodwikipedia.databinding.FragmentContainerBinding
import android.bignerdranch.foodwikipedia.screen.Header
import android.bignerdranch.foodwikipedia.screen.Settings
import android.content.Context
import android.media.MediaPlayer
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.commit

class FragmentContainer: AppCompatActivity(), Navigator {
    private val binding by lazy {
        FragmentContainerBinding.inflate(layoutInflater)
    }

    private var mediaPlayer: MediaPlayer? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setMusic()

        if (savedInstanceState == null) {
            replaceFragments(supportFragmentManager, Header.newInstance())
        }
    }

     override fun setMusic() {
        val preferences = getSharedPreferences(Settings.MUSIC_PREFERENCES, Context.MODE_PRIVATE)
        val isChecked = preferences.getBoolean(Settings.MUSIC_STATE, true)

        if (isChecked) {
            if (mediaPlayer == null) {
                mediaPlayer = MediaPlayer.create(this, R.raw.background_music)
                mediaPlayer?.isLooping = true
            }
            mediaPlayer?.start()
        } else {
            if (mediaPlayer != null) {
                mediaPlayer?.release()
                mediaPlayer = null
            }

        }
    }


    override fun launchFragment(manager: FragmentManager, fragment: Fragment) {
        manager.commit {
            setCustomAnimations(
                R.anim.slide_in,
                R.anim.fade_out,
                R.anim.fade_in,
                R.anim.slide_out
            )
            replace(R.id.fragmentContainer, fragment)
            addToBackStack(null)
        }
    }

    override fun replaceFragments(manager: FragmentManager, fragment: Fragment) {
        manager.commit {
            setCustomAnimations(
                R.anim.slide_in,
                R.anim.fade_out,
                R.anim.fade_in,
                R.anim.slide_out
            )
            replace(R.id.fragmentContainer, fragment)
        }
    }

    override fun onStart() {
        super.onStart()
        mediaPlayer?.start()
    }

    override fun onStop() {
        super.onStop()
        mediaPlayer?.pause()
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer?.stop()
        mediaPlayer?.release()
    }
}