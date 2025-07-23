package com.example.pruebatarjeta


import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2

class MainActivity : AppCompatActivity() {

    private lateinit var viewPager: ViewPager2
    private lateinit var adapter: AdAdapter
    private val handler = Handler(Looper.getMainLooper())
    private var currentItem = 0
    private var directionForward = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewPager = findViewById(R.id.viewPager)

        val ads = listOf(
            Ad("Ad 1", R.drawable.ic_launcher_foreground),
            Ad("Ad 2", R.drawable.ic_launcher_foreground),
            Ad("Ad 3", R.drawable.ic_launcher_foreground)
        )

        adapter = AdAdapter(ads)
        viewPager.adapter = adapter

        viewPager.setPageTransformer(DepthPageTransformer())

        startAdRotation()
    }

    private fun startAdRotation() {
        val runnable = object : Runnable {
            override fun run() {
                if (directionForward) {
                    if (currentItem == adapter.itemCount - 1) {
                        directionForward = false
                    } else {
                        currentItem++
                    }
                } else {
                    if (currentItem == 0) {
                        directionForward = true
                    } else {
                        currentItem--
                    }
                }

                viewPager.setCurrentItem(currentItem, true)
                handler.postDelayed(this, 6000)
            }
        }

        handler.post(runnable)
    }

    override fun onDestroy() {
        super.onDestroy()
        handler.removeCallbacksAndMessages(null)
    }
}