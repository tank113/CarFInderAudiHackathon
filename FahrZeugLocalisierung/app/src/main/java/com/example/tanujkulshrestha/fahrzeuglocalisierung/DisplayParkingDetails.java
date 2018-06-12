package com.example.tanujkulshrestha.fahrzeuglocalisierung;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tanujkulshrestha.fahrzeuglokalisierung.database.AddParkingDataToDB;
import com.squareup.picasso.Picasso;

public class DisplayParkingDetails extends AppCompatActivity {

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
        setContentView(R.layout.activity_display_parking_details);
        System.out.println("inside display");
        Button like = (Button) findViewById(R.id.likeButton);
        Button dislike = (Button) findViewById(R.id.dislikeButton);
        like.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                onLikeButtonClick();
            }
        });
        dislike.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                onDislikeButtonClick();
            }
        });
        Bundle extras = getIntent().getExtras();
        if(extras == null) {
            car_id= null;
            row=null;
            zone_id=null;
            slot=null;
            building=null;
            identifier=null;
            zone_image=null;


        } else {
            car_id= extras.getString("carID");
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


    public void onLikeButtonClick(){
        Bundle extras = getIntent().getExtras();
        if(extras == null) {
            parking_id= null;
        } else {
            parking_id= extras.getString("parking_id");
        }
        AddParkingDataToDB addParkingDataToDB = new AddParkingDataToDB();
        addParkingDataToDB.new CreateNewProduct(getApplicationContext()).execute(parking_id, car_id);

    }

    public void onDislikeButtonClick(){
        Intent intent = new Intent(this, ScanParkingBarcodeScreenActivity.class);
        startActivity(intent);
    }

}
