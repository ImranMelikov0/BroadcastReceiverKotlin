package com.imranmelikov.broadcastreceiver

import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    private lateinit var battery: Battery
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        battery= Battery()
    }

    override fun onResume() {
        super.onResume()

        val filter=IntentFilter()
        filter.addAction("android.intent.action.BATTERY_LOW")

        registerReceiver(battery,filter)
    }

    override fun onStop() {
        super.onStop()

        unregisterReceiver(battery)
    }
}