package com.example.azan

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.app.AlarmManager
import android.content.Context.ALARM_SERVICE
import android.support.v4.content.ContextCompat.getSystemService
import android.app.PendingIntent
import android.content.Intent
import android.content.Context.ALARM_SERVICE
import android.support.v4.content.ContextCompat.getSystemService
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*


class MainActivity : AppCompatActivity() {
    private val NOTIFICATION_REMINDER_NIGHT = 1
    companion object{
        lateinit var STOPBTN : View
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //set the alarm manager
        val notifyIntent = Intent(this, AzanReciever::class.java)
        val pendingIntent = PendingIntent.getBroadcast(
            this,
            NOTIFICATION_REMINDER_NIGHT,
            notifyIntent,
            PendingIntent.FLAG_UPDATE_CURRENT
        )
        val alarmManager = this.getSystemService(ALARM_SERVICE) as AlarmManager

        val calendar = Calendar.getInstance()
        calendar.setTimeInMillis(System.currentTimeMillis())
        calendar.set(Calendar.HOUR_OF_DAY, 23)
        calendar.set(Calendar.MINUTE, 8)
        calendar.set(Calendar.SECOND, 1)
        alarmManager.setRepeating(
            AlarmManager.RTC_WAKEUP, calendar.timeInMillis,
            (1000 * 60 * 60 * 24).toLong(), pendingIntent
        )

        STOPBTN = findViewById(R.id.stopAzan)
    }
}
