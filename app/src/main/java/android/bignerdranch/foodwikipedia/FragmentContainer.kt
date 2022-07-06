package android.bignerdranch.foodwikipedia

import android.bignerdranch.foodwikipedia.databinding.FragmentContainerBinding
import android.bignerdranch.foodwikipedia.extensions.Contract
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.media.MediaPlayer
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

class FragmentContainer: AppCompatActivity(), Contract {
    private lateinit var binding: FragmentContainerBinding
    private lateinit var mediaPlayer: MediaPlayer

    private val mFLAG_TRANSLUCENT_NAVIGATION = WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = FragmentContainerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupUI()

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


    private fun setupUI() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        window.setFlags(mFLAG_TRANSLUCENT_NAVIGATION, mFLAG_TRANSLUCENT_NAVIGATION)
        supportActionBar?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        onBackPressed(); return true
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