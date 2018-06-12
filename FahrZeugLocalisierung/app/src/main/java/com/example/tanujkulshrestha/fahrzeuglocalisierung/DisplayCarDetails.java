package com.example.tanujkulshrestha.fahrzeuglocalisierung;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class DisplayCarDetails extends AppCompatActivity {

    String car_id;
    String model;
    String colour;
    String factory;
    String image;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_car_details);
        Log.e("MainActivity", "Errormain");
        Button like = (Button) findViewById(R.id.likeButton);
        Button dislike = (Button) findViewById(R.id.dislikeButton);
        Bundle extras = getIntent().getExtras();
        if(extras == null) {
            car_id= null;
            model= null;
            colour= null;
            factory= null;
            image= null;

        } else {
            car_id= extras.getString("carID");
            model= extras.getString("model");
            colour= extras.getString("colour");
            factory= extras.getString("factory");
            image= extras.getString("image");
        }

        ImageView carImage = (ImageView) findViewById(R.id.imageView18);
        Picasso mPicasso = Picasso.get();
        mPicasso.load(image).into(carImage);

        TextView carID = (TextView) findViewById(R.id.carID);
        carID.setText(car_id);

        TextView modelID = (TextView) findViewById(R.id.textView29);
        modelID.setText(model);

        TextView factoryPlace = (TextView) findViewById(R.id.prodPlace);
        factoryPlace.setText(factory);

        TextView colorCar = (TextView) findViewById(R.id.color);
        colorCar.setText(colour);


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

    }

    public void onLikeButtonClick(){
        Intent intent = new Intent(this, ScanParkingBarcodeScreenActivity.class);
        intent.putExtra("carID", car_id);
        startActivity(intent);

    }

    public void onDislikeButtonClick(){
        Intent intent = new Intent(this, ScanCarBarcodeScreenActivity.class);
        startActivity(intent);
    }

    }

