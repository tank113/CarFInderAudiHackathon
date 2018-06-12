package com.example.tanujkulshrestha.fahrzeuglocalisierung;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class ParkingSpotDetails extends AppCompatActivity {

    double latitude;
    double longitude;
    String parking_id;
    String car_id;
    String row;
    String slot;
    String zone_id;
    String building;
    String identifier;
    String zone_image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parking_spot_details);
        System.out.println("inside display");
        Button navigate = (Button) findViewById(R.id.navigate);
        Button home = (Button) findViewById(R.id.home);
        navigate.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                onNavigateButtonClick();
            }
        });
        home.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                onhomeButtonClick();
            }
        });

        Bundle extras = getIntent().getExtras();
        if(extras == null) {
            latitude= 0;
            longitude=0;
            row =null;
            slot = null;
            zone_id =null;
            building = null;
            identifier = null;
            zone_image = null;


        } else {
            latitude= extras.getDouble("latitude");
            longitude= extras.getDouble("longitude");
            row= extras.getString("row");
            slot= extras.getString("slot");
            zone_id= extras.getString("zone_id");
            building= extras.getString("building");
            identifier= extras.getString("identifier");
            zone_image= extras.getString("zone_image");

        }
        ImageView parkingImage = (ImageView) findViewById(R.id.dynimg);

        Picasso mPicasso = Picasso.get();
        mPicasso.load(zone_image).into(parkingImage);


        TextView redHeaderSlot = (TextView) findViewById(R.id.slot);
        System.out.println("check" + row + slot);
        redHeaderSlot.setText(row+ " " +slot);


        TextView zoneID = (TextView) findViewById(R.id.zoneid);
        zoneID.setText("Zone:" + " " + zone_id);

        TextView buildingNo = (TextView) findViewById(R.id.buildnr);
        buildingNo.setText(building + "-" + identifier);


    }


    public void onNavigateButtonClick(){
        String URI = "geo:="+latitude+ "," +longitude+ "?q=" + latitude + "," + longitude;
        System.out.println("URI"+ URI);
        Uri gmmIntentUri = Uri.parse(URI);
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");
        startActivity(mapIntent);

    }

    public void onhomeButtonClick(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }


}
