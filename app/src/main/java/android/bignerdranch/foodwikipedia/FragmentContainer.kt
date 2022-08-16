package android.bignerdranch.foodwikipedia

import android.bignerdranch.foodwikipedia.databinding.FragmentContainerBinding
import android.bignerdranch.foodwikipedia.screen.Header
import android.bignerdranch.foodwikipedia.screen.Settings
import android.content.Context
import android.media.MediaPlayer
import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.commit
import kotlin.system.exitProcess

class FragmentContainer: AppCompatActivity(), Navigator {
    private val binding by lazy {
        FragmentContainerBinding.inflate(layoutInflater)
    }

    private var mediaPlayer: MediaPlayer? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setMusic()
        setAppTheme()

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

    override fun setAppTheme() {
        val preferences = getSharedPreferences(Settings.THEME_PREFERENCES, Context.MODE_PRIVATE)
        val pickedTheme = preferences.getString(Settings.THEME_STATE, "none")

        when (pickedTheme) {
            "Light" ->  {
                setTheme(R.style.Theme_FoodWikipedia_Light)

                try {
                    findViewById<ImageButton>(R.id.arrowBack)
                        .setImageResource(R.drawable.ic_baseline_arrow_back_light_24)

                    findViewById<ImageButton>(R.id.toSettingsImageButton)
                        .setImageResource(R.drawable.ic_baseline_settings_light_24)

                    findViewById<TextView>(R.id.screenLabel).setTextColor(resources.getColor(R.color.white))
                } catch (e: Exception) {}

            }
            "Dark" -> {
                setTheme(R.style.Theme_FoodWikipedia_Night)

                try {
                    findViewById<ImageButton>(R.id.arrowBack)
                        .setImageResource(R.drawable.ic_baseline_arrow_back_dark_24)

                    findViewById<ImageButton>(R.id.toSettingsImageButton)
                        .setImageResource(R.drawable.ic_baseline_settings_dark_24)

                    findViewById<TextView>(R.id.screenLabel).setTextColor(resources.getColor(R.color.black))

                } catch (e: Exception) {}

            }
            "none" -> Unit
            else ->{
                Toast.makeText(this, getString(R.string.an_error_occurred_toast), Toast.LENGTH_SHORT).show()
                exitProcess(0)
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
            addToBackStack("Header")

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