package android.bignerdranch.androidpractice

import android.bignerdranch.androidpractice.databinding.FragmentContainerBinding
import android.bignerdranch.androidpractice.extensions.Contract
import android.bignerdranch.androidpractice.extensions.showToast
import android.media.MediaPlayer
import android.os.Bundle
import android.os.PersistableBundle
import android.view.KeyEvent
import android.view.MenuItem
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import java.security.Key

class FragmentContainer: AppCompatActivity(), Contract {
    private lateinit var binding: FragmentContainerBinding

    private lateinit var mediaPlayer: MediaPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = FragmentContainerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mediaPlayer = MediaPlayer.create(this, R.raw.background_music)

        if (savedInstanceState == null)
            launchHeader()

    }

    override fun launchFragment(manager: FragmentManager, fragment: Fragment) {
        manager.beginTransaction()
            .addToBackStack(null)
            .replace(R.id.fragmentContainer, fragment)
            .commit()
    }

    private fun launchHeader() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, Header.newInstance())
            .commit()
    }



    override fun onStart() {
        super.onStart()
        mediaPlayer.start()
    }

    override fun onStop() {
        super.onStop()
        mediaPlayer.pause()
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer.stop()
        mediaPlayer.release()
    }

}