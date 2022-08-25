package android.bignerdranch.foodwikipedia.ui.application


import android.app.Activity
import android.app.Application
import android.os.Bundle
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import androidx.lifecycle.ProcessLifecycleOwner
import com.google.android.gms.ads.*
import com.google.android.gms.ads.appopen.AppOpenAd
import java.util.*

/**
 *     Created by Krupko Illa on 25.08.2022
 */

class App : Application() {
    private lateinit var appOpenAdManager: AppOpenAdManager

    private val AD_UNIT_ID = "ca-app-pub-3467896291108690/5015309649"


    override fun onCreate() {
        super.onCreate()
        appOpenAdManager = AppOpenAdManager()
        MobileAds.initialize(this) {}
        registerActivityLifecycleCallbacks(appOpenAdManager)
        ProcessLifecycleOwner.get().lifecycle.addObserver(appOpenAdManager)

    }


    private inner class AppOpenAdManager: ActivityLifecycleCallbacks, LifecycleObserver {
        private var currentActivity: Activity? = null


        private var appOpenAd: AppOpenAd? = null
        private var isLoadingAd = false
        var isShowingAd = false

        private var loadTime = 0L


        @OnLifecycleEvent(Lifecycle.Event.ON_START)
        fun onStart() {
            showAdIfAvailable()
        }



        fun loadAd() {
            if (isAdAvailable()) return

            val loadCallback = object : AppOpenAd.AppOpenAdLoadCallback() {
                override fun onAppOpenAdLoaded(ad: AppOpenAd?) {
                    appOpenAd = ad
                    loadTime = Date().time
                }

                override fun onAppOpenAdFailedToLoad(error: LoadAdError?) {}
            }

            val request = AdRequest.Builder().build()
            AppOpenAd.load(
                this@App, AD_UNIT_ID, request, AppOpenAd.APP_OPEN_AD_ORIENTATION_PORTRAIT, loadCallback
            )
        }

        private fun wasLoadTimeLessThanNHoursAgo(numHours: Long): Boolean {
            val dateDifference = Date().time - this.loadTime
            val numMillisecondsPerHour = 3600000
            return (dateDifference < (numMillisecondsPerHour * numHours))
        }

        fun isAdAvailable() = appOpenAd != null && wasLoadTimeLessThanNHoursAgo(4)

        fun showAdIfAvailable() {
            if (!isShowingAd && isAdAvailable()) {
                val fullScreenContentCallback = object : FullScreenContentCallback() {
                    override fun onAdShowedFullScreenContent() {
                        isShowingAd = true
                    }

                    override fun onAdDismissedFullScreenContent() {
                        AppOpenAdManager().appOpenAd = null
                        isShowingAd = false
                        loadAd()
                    }

                    override fun onAdFailedToShowFullScreenContent(error: AdError?) {}
                }
                appOpenAd?.show(currentActivity, fullScreenContentCallback)
            } else {
                loadAd()
            }
        }


        override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {}

        override fun onActivityStarted(activity: Activity) {
            currentActivity = activity
        }

        override fun onActivityResumed(activity: Activity) {
            currentActivity = activity
        }

        override fun onActivityPaused(activity: Activity) {}

        override fun onActivityStopped(activity: Activity) {}

        override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {}

        override fun onActivityDestroyed(activity: Activity) {
            currentActivity = null
        }
    }
}