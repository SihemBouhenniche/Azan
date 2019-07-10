package com.example.azan

import android.app.IntentService
import android.content.Intent
import android.support.v4.app.NotificationManagerCompat
import android.app.PendingIntent
import android.R
import android.app.Notification
import android.media.MediaPlayer


class AzanIntentService : IntentService("AzanIntentService") {
    private val NOTIFICATION_ID = 3
    private var mp: MediaPlayer = MediaPlayer()
    override fun onHandleIntent(intent: Intent?) {
        //play azan
        if(!mp.isPlaying){
            mp = MediaPlayer.create(this,resources.getIdentifier("azan","raw",this.packageName))
            mp.start()
        }

        //handle notifation
        val builder = Notification.Builder(this)
        builder.setContentTitle("وقت الصلاة")
        builder.setContentText("حان الآن موعد الصلاة")
        builder.setSmallIcon(R.drawable.ic_media_play)
        val notifyIntent = Intent(this, MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(this, 2, notifyIntent, PendingIntent.FLAG_UPDATE_CURRENT)
        //to be able to launch your activity from the notification
        builder.setContentIntent(pendingIntent)
        val notificationCompat = builder.build()
        val managerCompat = NotificationManagerCompat.from(this)
        managerCompat.notify(NOTIFICATION_ID, notificationCompat)

        //function to stop azan
        MainActivity.STOPBTN.setOnClickListener {
            stopAzan()
        }
    }

    fun stopAzan(){
        if(mp.isPlaying){
            mp.stop()
        }
    }
}