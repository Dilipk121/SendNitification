package com.example.sendnitification

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

class MainActivity : AppCompatActivity() {

    val CHANNEL_ID = "channelId"
    val CHANNEL_NAME = "channelName"
    val notification = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        createNotificationChannel()

        //Pending Intent
        val intent = Intent(this,MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(this,0,intent,PendingIntent.FLAG_MUTABLE)


        val notification_msg = NotificationCompat.Builder(this,CHANNEL_ID)
            .setContentTitle("30 Days as Android Developer and Job")
            .setContentText("All The Best Dilip for Your Nice Job as a Android Team Lead")
            .setSmallIcon(R.drawable.message)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setContentIntent(pendingIntent)// pending intent pass here
            .build()

        val notificationManager = NotificationManagerCompat.from(this)
        val btn = findViewById<Button>(R.id.notify_btn)

        btn.setOnClickListener(){

            if (ActivityCompat.checkSelfPermission(
                    this,
                    Manifest.permission.POST_NOTIFICATIONS
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                Toast.makeText(this, "Plz 'ON' Notification", Toast.LENGTH_SHORT).show()
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return@setOnClickListener
            }
            notificationManager.notify(notification,notification_msg)

        }


    }


    private fun createNotificationChannel(){

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){

            val channel = NotificationChannel(CHANNEL_ID,CHANNEL_NAME,NotificationManager.IMPORTANCE_HIGH).apply {

                description = "This is my Notification Channel"
                lightColor = Color.BLUE
                enableLights(true)
            }
            val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            manager.createNotificationChannel(channel)

        }

    }
}