package com.example.tanujkulshrestha.fahrzeuglocalisierung;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class ScanCarBarcodeScreenActivity extends AppCompatActivity {

    private boolean isCameraPermissionGranted = false;
    static final int PERMISSION_REQUEST_CAMERA = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_barcode_screen);
        Log.e("MainActivity", "Errormain");
        Button scan = (Button) findViewById(R.id.scanButton);
        scan.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                onScanBarcodeClick();
            }
        });

    }

    public void onScanBarcodeClick(){
        if (!isCameraPermissionGranted) {
            if(!checkCameraPermission())
                return;
        }
        Intent intent = new Intent(this, ScanCarBarcodeActivity.class);
        startActivity(intent);
    }

    private boolean checkCameraPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            requestCameraPermission();
        }
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                == PackageManager.PERMISSION_GRANTED) {
            isCameraPermissionGranted = true;
        }
        return isCameraPermissionGranted;
    }

    private void requestCameraPermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                Manifest.permission.CAMERA)) {
            DialogInterface.OnClickListener callback = new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    ActivityCompat.requestPermissions(ScanCarBarcodeScreenActivity.this,
                            new String[]{Manifest.permission.CAMERA},
                            PERMISSION_REQUEST_CAMERA);
                }
            };
            showAlert("Camera permission was not granted",
                    "Error!",
                    "Ask again",
                    callback,
                    true);
        } else {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.CAMERA},
                    PERMISSION_REQUEST_CAMERA);
        }
    }

    private void showAlert(String message,
                           String title,
                           String buttonTitle,
                           DialogInterface.OnClickListener buttonCallback,
                           boolean showCloseButton) {
        MessageAlertFragment alert = MessageAlertFragment.newInstance(this, title, message);
        if (showCloseButton)
            alert.setNegativeButton("Close", null);
        if (buttonTitle != null)
            alert.setPositiveButton(buttonTitle, buttonCallback);
        alert.show(this.getFragmentManager(), "main_activity_alert");
    }



}
