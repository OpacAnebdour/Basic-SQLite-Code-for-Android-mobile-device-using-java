package com.opac.network;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {


    SQLiteHelper db = new SQLiteHelper(this);
    //ListView list;
    private String dislay = "";

//    private String ip;
    private String mac;
    private String SSID;
    private String SSi;
    private String speed;
    private String freq;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @SuppressLint({"WrongViewCast", "SetTextI18n"})
    public void getBtn(View view) {

        Intent myintent = new Intent(this, Main2Activity.class);

        boolean result = db.addData(dislay);

        if(result == true){
            Toast.makeText(MainActivity.this, "OK", Toast.LENGTH_SHORT).show();
            // showData();
        }else{
            Toast.makeText(MainActivity.this, "NO", Toast.LENGTH_SHORT).show();
        }

        startActivity(myintent);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @SuppressLint("HardwareIds")
    public void checkbtn(View view) {
        WifiManager wifimanager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        assert wifimanager != null;
        WifiInfo connection = wifimanager.getConnectionInfo();

  //      ip = "\nipv4: " + connection.getIpAddress();
        mac = "\n MAC:"+connection.getMacAddress();
        SSi = "\n SSi: " + connection.getRssi();
        SSID = "\nSSID :" + connection.getSSID();
        speed = "\nSpeed : "+ connection.getLinkSpeed();
        freq = "\nFrequence : "+ connection.getFrequency();

        dislay += "\nNetwork name :" + connection.getSSID() + "\n RSSi (dbm) : " + connection.getRssi()+ "\nFrequence Hz : "+ connection.getFrequency() +"\nSpeed bit/s : "+ connection.getLinkSpeed() +"\n MAC:"+connection.getMacAddress();

        final TextView result = findViewById(R.id.textView2);
        final Button btn = findViewById(R.id.check);

        final String finalDislay = dislay;
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                result.setText(finalDislay);
            }
        });
    }
}
