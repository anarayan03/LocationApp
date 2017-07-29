package com.example.hp.newgpsservice;

import android.app.IntentService;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.support.v4.app.ActivityCompat;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

public class MyIntentService extends IntentService implements OnSuccessListener<Location>
{
    private FusedLocationProviderClient mFusedLocationClient;
    private double lati;
    private double longi;
    public MyIntentService()
    {
        super("MyIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent)
    {
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mFusedLocationClient.getLastLocation().addOnSuccessListener(this);
    }
    @Override
    public void onSuccess(Location location)
    {
        Intent intent = new Intent("com.example.hp.newgpsservice");
        if(location != null)
        {
            lati=location.getLatitude();
            longi=location.getLongitude();
            intent.putExtra("adi",String.valueOf(lati));
            intent.putExtra("nar",String.valueOf(longi));
            sendBroadcast(intent);
        }
        else
        {
            Toast.makeText(this,"Plzz On Your Location",Toast.LENGTH_LONG).show();
        }
    }
}

