package com.synbustech.notekeeper

import android.content.Context
import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent

class NoteGetTogetherHelper(val context: Context, val lifeCycle: Lifecycle) : LifecycleObserver {
    init {
        lifeCycle.addObserver(this)
    }

    val TAG = this::class.simpleName
    var currentLat = 0.0
    var currentLon = 0.0

    val locManager = PseudoLocationManager(context) { lat, lon ->
        currentLat = lat
        currentLon = lon
        Log.d(TAG, "Location CallBack Lat: $currentLat Lon: $currentLon")
    }

    val msgManager = PseudoMessagingManager(context)
    var msgConnection: PseudoMessagingConnection? = null

    fun sendMessage(note: NoteInfo){
        val getTogetherMsg = "$currentLat | $currentLon | ${note.title} | ${note.course?.title}"
        msgConnection?.send(getTogetherMsg)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun startHandler() {
        locManager.start()

        msgManager.connect { connection ->
            Log.d(TAG, "Connection callback - Lifecycle state: ${lifeCycle.currentState}")
            if (lifeCycle.currentState.isAtLeast(Lifecycle.State.STARTED))
                msgConnection = connection
            else
                connection.disconnect()
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun stopHandler() {
        locManager.stop()

        msgConnection?.disconnect()
    }
}