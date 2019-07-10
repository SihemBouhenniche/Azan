package com.example.azan

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class AzanReciever : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        val intent = Intent(context, AzanIntentService::class.java)
        context!!.startService(intent)
    }
}