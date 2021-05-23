package com.example.wided

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent

import androidx.core.app.NotificationCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import java.util.*

class MyFirebaseMessagingService : FirebaseMessagingService() {
    lateinit var title: String
    lateinit var message: String
    var CHANNEL_ID="CHANNEL"

    lateinit var manager: NotificationManager

    override fun onMessageReceived(remotemessage: RemoteMessage) {
        super.onMessageReceived(remotemessage)
        title=remotemessage.notification!!.title!!
        message=remotemessage.notification!!.body!!


        manager=this.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        sendNotification()

    }

    override fun onNewToken(p0: String) {
        super.onNewToken(p0)
    }


    private fun sendNotification() {
        var intent= Intent(applicationContext,MainActivity::class.java)

        intent.putExtra("title",title)
        intent.putExtra("message",message)
        intent.flags=Intent.FLAG_ACTIVITY_CLEAR_TOP
        intent.flags=Intent.FLAG_ACTIVITY_NEW_TASK

        if(android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O ){
            var channel = NotificationChannel(CHANNEL_ID,"pushnotification",NotificationManager.IMPORTANCE_HIGH)
           manager.createNotificationChannel(channel)
        }
        var builder=NotificationCompat.Builder(this,CHANNEL_ID)
            .setContentTitle(title)
            .setSmallIcon(R.drawable.healthy)
            .setAutoCancel(true)
            .setContentText(message)

        var pendingIntent=PendingIntent.getActivity(applicationContext,0,intent,PendingIntent.FLAG_ONE_SHOT)

        builder.setContentIntent(pendingIntent)
        manager.notify(0,builder.build())

    }

}



