package com.example.tanujkulshrestha.fahrzeuglocalisierung;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ParkingDataAddedToDB extends AppCompatActivity {

    String successMsg;
    String errorMsg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parking_data_added_to_db);
        Button getBackHome = (Button) findViewById(R.id.backtohome);
        getBackHome.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                onBackHomeClick();
            }
        });


        Bundle extras = getIntent().getExtras();
        if (extras == null) {
            successMsg = null;
            errorMsg = null;


        } else {
            successMsg = extras.getString("Success");
            errorMsg = extras.getString("Error");

        }

        TextView textToBeDispalayed = (TextView) findViewById(R.id.textView22);

        if (successMsg.contains("Success")) {
            textToBeDispalayed.setText("You have successfully parked the vehicle. Thank You for using Fahrzeugfinder");

        } else if (errorMsg.contains("Error")) {
            textToBeDispalayed.setText("Sorry! Your data is not saved. Are you sure you have scanned the barcodes for Car and Parking correctly?");
        } else {
            textToBeDispalayed.setText("Oops!! Looks like you are not connected to Internet. Please connect and try again");
        }


    }
    public void onBackHomeClick(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
    }


