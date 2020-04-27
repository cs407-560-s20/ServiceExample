package com.example.serviceexample

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log

class MyReceiver : BroadcastReceiver() {

    private val TAG = "MyReceiver"

    override fun onReceive(context: Context, intent: Intent) {
        // This method is called when the BroadcastReceiver is receiving an Intent broadcast.

        // Handle the received broadcast message

        val file = intent.getStringExtra("file")

        Log.d(TAG, "DownloadReceiver: file has been downloaded: $file")

    }
}
