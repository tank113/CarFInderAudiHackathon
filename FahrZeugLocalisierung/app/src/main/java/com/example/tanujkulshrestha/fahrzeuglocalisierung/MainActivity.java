package com.example.tanujkulshrestha.fahrzeuglocalisierung;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private boolean isCameraPermissionGranted = false;
    static final int PERMISSION_REQUEST_CAMERA = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.e("MainActivity", "Errormain");
        Button scan = (Button) findViewById(R.id.scanButton);
        Button search = (Button) findViewById(R.id.searchButton);
        scan.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                onScanBarcodeClick();
            }
        });
        search.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                onLocateCarClick();
            }
        });
    }

    private void onScanBarcodeClick() {
        Intent intent = new Intent(this, ScanCarBarcodeScreenActivity.class);
        startActivity(intent);
    }

    private void onLocateCarClick() {
        Intent intent = new Intent(this, LocateCarActivity.class);
        startActivity(intent);
    }

}
