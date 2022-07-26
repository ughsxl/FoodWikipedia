package android.bignerdranch.foodwikipedia

import android.bignerdranch.foodwikipedia.databinding.FragmentContainerBinding
import android.bignerdranch.foodwikipedia.screen.Header
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.commit

class FragmentContainer: AppCompatActivity(), Navigator {
    private lateinit var binding: FragmentContainerBinding
    //private lateinit var mediaPlayer: MediaPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = FragmentContainerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //mediaPlayer = MediaPlayer.create(this, R.raw.background_music)

        if (savedInstanceState == null) {
            replaceFragments(supportFragmentManager, Header.newInstance())
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