package com.nmai.beentogether.ui.setting

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.media.session.MediaSessionManager
import android.os.Build
import android.widget.RemoteViews
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.nmai.beentogether.R
import com.nmai.beentogether.repository.user.User
import com.nmai.beentogether.ui.MainActivity

class Notification(val context: Context, val CHANNEL_ID: String)  {
    lateinit var name: String
    lateinit var descriptionText: String
    var importance: Int = 0
    lateinit var builder: NotificationCompat.Builder
    val notificationLayout = RemoteViews(context.packageName, R.layout.notification_layout)
    init {
        val intent = Intent(context, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_TASK_ON_HOME
        }
        val pendingIntent: PendingIntent = PendingIntent.getActivity(context, 0, intent, 0)
        name = context.getString(R.string.app_name)
        descriptionText = context.getString(R.string.app_name)
        builder = NotificationCompat.Builder(context, CHANNEL_ID)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setOngoing(true)
            .setContentTitle("My notification")
            .setContentText("Hello World!")
            .setContentIntent(pendingIntent)
            .setSmallIcon(R.drawable.ic_heart)
            .setCustomContentView(notificationLayout)
    }
    fun createNotificationChannel(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // Create the NotificationChannel
            importance = NotificationManager.IMPORTANCE_DEFAULT
            val mChannel = NotificationChannel(CHANNEL_ID, name, importance)
            mChannel.description = descriptionText
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            val notificationManager =
                context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(mChannel)
        }
    }
    fun creatNotification(user1 : String, user2 : String, gender1 : String, gender2: String, date : String){
        with(NotificationManagerCompat.from(context)) {
            // notificationId is a unique int for each notification that you must define
            setContent(user1, user2, gender1, gender2, date)
            notify(1, builder.build())
        }
    }
    fun stopNotification(){
        val ns = Context.NOTIFICATION_SERVICE
        val manager = context.getSystemService(ns) as NotificationManager
        manager.cancel(1)
    }
    fun setContent(user1 : String, user2 : String, gender1 : String, gender2: String, date : String){
        notificationLayout.setTextViewText(R.id.nick_name_user_1, user1)
        notificationLayout.setTextViewText(R.id.nick_name_user_2, user2)
        notificationLayout.setTextViewText(R.id.btn_heart, date)
        if(gender1 == "female") notificationLayout.setImageViewResource(R.id.gender_user_1, R.drawable.ic_gender_female)
        else if(gender1 == "male") notificationLayout.setImageViewResource(R.id.gender_user_1, R.drawable.ic_gender_male)
        if(gender2 == "female") notificationLayout.setImageViewResource(R.id.gender_user_2, R.drawable.ic_gender_female)
        else if(gender2 == "male") notificationLayout.setImageViewResource(R.id.gender_user_2, R.drawable.ic_gender_male)

    }
}