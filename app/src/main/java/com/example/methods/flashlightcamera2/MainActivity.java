package com.example.methods.flashlightcamera2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;


public class MainActivity extends AppCompatActivity {

    private CameraManager camManager;
    private String camID;
    private Button on;
    private Button off;
    private Boolean isLightON;
    private MediaPlayer mp;
    //private boolean isInBackground;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d("FlashLightActivity", "onCreate()");

        setContentView(R.layout.activity_main);
        on = (Button)findViewById(R.id.onButton);
        off = (Button)findViewById(R.id.offButton);
        isLightON =false;


        camManager = (CameraManager)getSystemService(CAMERA_SERVICE);
        try {
            camID = camManager.getCameraIdList()[0];
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }

        off.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (isLightON) {
                        turnOffFlashLight();
                        isLightON = false;
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });


        on.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (!isLightON) {
                        turnOnFlashLight();
                        isLightON = true;
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }



    @Override
    protected void onPause(){

        super.onPause();
        turnOffFlashLight();
        //Log.d("flashlight", "on pause");
    }

    @Override
    protected void onResume(){
        super.onResume();
        turnOffFlashLight();
    }

    public void turnOnFlashLight() {

        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                camManager.setTorchMode(camID, true);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void turnOffFlashLight() {

        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                camManager.setTorchMode(camID, false);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }




    }




