package com.synbustech.notekeeper

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.util.Log

class PseudoLocationManager (private val context: Context,
                             private val callback: (Double, Double) -> Unit) : Runnable {

    private val TAG = this::class.simpleName

    private val latitudes = doubleArrayOf(23.9865, 67.9087, 41.5643)
    private val longitudes = doubleArrayOf(-41.5643, -67.9087, -23.9865)
    private var locationIndex = 0

    private val callackMilliSecs = 3000L

    private var enabled = false
    private var postHandler = Handler(Looper.getMainLooper())

    fun start() {
        enabled = true
        Log.d(TAG, "Location Manager started")
        triggerCallbackAndScheduleNext()
    }

    fun stop() {
        enabled = false
        postHandler.removeCallbacks(this)
        Log.d(TAG, "Location Manager stopped")
    }

    private fun triggerCallbackAndScheduleNext() {
        if (enabled)
            postHandler.postDelayed(
                {
                    run()
                }, callackMilliSecs
            )
    }

    override fun run() {
        callback(latitudes[locationIndex], longitudes[locationIndex])
        if (locationIndex == 2)
            locationIndex = 0
        locationIndex += 1
        triggerCallbackAndScheduleNext()
    }
}