package com.learning.helloworld

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.core.app.NotificationManagerCompat
import com.learning.helloworld.LoginActivity.Companion.KEY_LOGIN

class MainActivity : AppCompatActivity() {
    private var button : Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val login = intent.extras?.get(KEY_LOGIN)
        val t = findViewById<TextView>(R.id.textView)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationManager = getSystemService(NotificationManager::class.java)
            val channel = NotificationChannel("notification_channel", "channel_01", NotificationManager.IMPORTANCE_DEFAULT)
            notificationManager.createNotificationChannel(channel)
            val notification = Notification.Builder(this, channel.id)
                .setSmallIcon(R.drawable.person_icon)
                .setContentTitle("Test notification")
                .build()
            notificationManager.notify(12, notification)
        }
    }
}
