package com.example.hp.newgpsservice;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
{
    String latit,longit;
    Button loc;
    TextView longitude,latitude;
    BroadcastReceiver mReceiver=new BroadcastReceiver()
    {
        @Override
        public void onReceive(Context context, Intent intent)
        {
            latit = intent.getStringExtra("adi");
            latitude.setText("LAT : " + latit);
            longit = intent.getStringExtra("nar");
            longitude.setText("LON : " + longit);
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loc=(Button)findViewById(R.id.button);
        latitude=(TextView)findViewById(R.id.textView);
        longitude=(TextView)findViewById(R.id.textView2);
    }
    public void getmylocation(View view)
    {
        Intent i = new Intent(this,MyIntentService.class);
        startService(i);
    }
    @Override
    protected void onPause()
    {
        super.onPause();
        unregisterReceiver(mReceiver);
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        registerReceiver(mReceiver,new IntentFilter("com.example.hp.newgpsservice"));
    }
}
