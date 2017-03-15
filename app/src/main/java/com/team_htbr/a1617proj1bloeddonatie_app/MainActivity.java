package com.team_htbr.a1617proj1bloeddonatie_app;

import android.app.Notification;
import android.app.NotificationManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

	Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

		btn = (Button)findViewById(R.id.btnNotification);
    }

    public void getNotification(View view) {
		NotificationManager notimngr = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
		Notification notif = new Notification.Builder(this)
			.setSmallIcon(R.drawable.ic_stat_name)
			.setContentTitle("hello world")
			.setContentText("Geef bloed!")
			.build();

		notimngr.notify(0, notif);
	}
}
