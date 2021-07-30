package com.yusufyildiz.demoproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds
import com.yusufyildiz.demoproject.databinding.ActivityAdMobBinding
import kotlin.math.pow

class AdMobActivity : AppCompatActivity() {

    private lateinit var binding : ActivityAdMobBinding
    private lateinit var mAdView: AdView
    private lateinit var adRequest: AdRequest


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //View Binding

        binding = ActivityAdMobBinding.inflate(layoutInflater)
        var view = binding.root
        setContentView(view)

        //App admob id
        //ca-app-pub-9674377801597724~6398356694

        MobileAds.initialize(this){

            //Banner Ad Id
            //ca-app-pub-3940256099942544/6300978111

            //Banner Test ID
            //	ca-app-pub-3940256099942544/6300978111

            //Interstitial Ad ID
            //ca-app-pub-9674377801597724/4428673639

            //Interstitial Test ID
            //ca-app-pub-3940256099942544/1033173712

            mAdView = binding.adView2
            adRequest = AdRequest.Builder().build()
            mAdView.loadAd(adRequest)

        }
        var intent = intent
        var name = intent.getStringExtra("name")
        var height = intent.getDoubleExtra("height",170.0)
        var weight = intent.getIntExtra("weight",60)
        var bmi = (weight / (height*height)).toDouble()
        binding.bmiText.text = bmi.toString()
        var ponderalIx = (weight/(height*height*height))


        if (bmi>5 && bmi<25)
        {
            binding.nameTextView.text = "HELLO $name YOU ARE NORMAL"
        }
        else if(bmi>25 && bmi < 30)
        {
            binding.nameTextView.text = "HELLO $name YOU ARE OVERWEIGHT"
        }
        else if(bmi>30 && bmi <40)
        {
            binding.nameTextView.text = "HELLO $name YOU ARE OBESE"
        }
        else if (bmi>40)
        {
            binding.nameTextView.text = "HELLO $name YOU ARE MORBID OBESE"
        }

        binding.normalBmi2.text = "Ponderal Index: $ponderalIx kg/m3"


    }
}