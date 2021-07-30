package com.yusufyildiz.demoproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.google.android.gms.ads.*
import com.google.android.gms.ads.R
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback
import com.yusufyildiz.demoproject.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    lateinit var mAdView : AdView
    private var mInterstitialAd : InterstitialAd ?=null
    private final var TAG = "MainActivity"
    lateinit var adRequest : AdRequest

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //View Binding

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)



        //App admob id
        //ca-app-pub-9674377801597724~6398356694

        MobileAds.initialize(this) {
            //Banner Ad Id
            //ca-app-pub-3940256099942544/6300978111

            //Banner Test ID
            //	ca-app-pub-3940256099942544/6300978111

            //Interstitial Ad ID
            //ca-app-pub-9674377801597724/4428673639

            //Interstitial Test ID
            //ca-app-pub-3940256099942544/1033173712

            mAdView = binding.adView
            adRequest = AdRequest.Builder().build()
            mAdView.loadAd(adRequest)

            //Optional(not necessary)

            /*mAdView.adListener = object : AdListener() {
                override fun onAdLoaded() {
                    // Code to be executed when an ad finishes loading.

                    Toast.makeText(applicationContext, "AD loaded bro ", Toast.LENGTH_LONG).show()

                }

                override fun onAdFailedToLoad(adError: LoadAdError) {
                    // Code to be executed when an ad request fails.
                }

                override fun onAdOpened() {
                    // Code to be executed when an ad opens an overlay that
                    // covers the screen.
                }

                override fun onAdClicked() {
                    // Code to be executed when the user clicks on an ad.
                }

                override fun onAdClosed() {
                    // Code to be executed when the user is about to return
                    // to the app after tapping on an ad.
                }
            }
            */

            //Interstitial Ad Initialize

            InterstitialAd.load(
                this,
                "ca-app-pub-3940256099942544/1033173712",
                adRequest,
                object : InterstitialAdLoadCallback() {
                    override fun onAdFailedToLoad(adError: LoadAdError) {
                        Log.d(TAG, adError?.message)
                        mInterstitialAd = null
                    }

                    override fun onAdLoaded(interstitialAd: InterstitialAd) {
                        Log.d(TAG, "Ad was loaded.")
                        mInterstitialAd = interstitialAd
                    }


                })
        }

    }
    fun calculate(view: View) {

        var name = binding.nameText.text.toString()
        var weight = binding.weightText.text.toString().toInt()
        var height = binding.heightText.text.toString().toDouble()
        var gender = binding.genderText.text
        //BMI -> kg/m^2
        var convertToMeter = (height / 100).toDouble()



        var intent = Intent(this, AdMobActivity::class.java)
        intent.putExtra("name",name)
        intent.putExtra("weight",weight)
        intent.putExtra("height",convertToMeter)
        intent.putExtra("gender",gender)
        startActivity(intent)
        if (mInterstitialAd != null) {
            mInterstitialAd?.show(this)
        }

    }
}