package com.example.serviceexample

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val TAG = "MainActivity"

    // Pretend these files are urls that the user wants to download
    private val listOfFakeFileNames = arrayListOf<String>("File-1", "File-2", "File-3", "File-4", "File-5",
                                                     "File-6", "File-7", "File-8", "File-9", "File-10")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    fun startServiceButton(view: View) {

        // Register the broadcast receiver
        registerMyReceiver()

        val intent = Intent(this, MyService::class.java)
        intent.putStringArrayListExtra("filesToDownload", listOfFakeFileNames)
        startService(intent) // not startActivity

        // Show a static text
        tv_downloading.text = "Downloading..."

    }

    private fun registerMyReceiver(){
        // Register a receiver to know the service is done
        val filter = IntentFilter()
        filter.addAction("downloadComplete")
        registerReceiver(DownloadReceiver(), filter)
    }



    private inner class DownloadReceiver : BroadcastReceiver(){
        override fun onReceive(context: Context, intent: Intent) {

            // Handle the received broadcast message

            val file = intent.getStringExtra("file")

            Log.d(TAG, "DownloadReceiver: $file has been downloaded")

            progress_bar.progress = progress_bar.progress + 10
            // This will look like : Downloading ... 1/10 etc.
            tv_downloading.text = "Downloading... ${progress_bar.progress/listOfFakeFileNames.size}/${listOfFakeFileNames.size}"

        }
    }


/*    override fun onStop() {
        super.onStop()
        unregisterReceiver(DownloadReceiver())
    }*/

}
