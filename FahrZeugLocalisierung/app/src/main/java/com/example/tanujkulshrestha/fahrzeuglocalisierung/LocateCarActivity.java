package com.example.tanujkulshrestha.fahrzeuglocalisierung;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LocateCarActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_locate_car);
        Log.e("MainActivity", "Errormain");
        Button scan = (Button) findViewById(R.id.submit);

        scan.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                onSubmitButtonClick();
            }
        });

    }

    private void onSubmitButtonClick() {

        EditText carIDToSearch = (EditText) findViewById(R.id.searchCarID);
        String carID= carIDToSearch.getText().toString();
        if(!carID.isEmpty()){
            RetrieveCarDetailsWithUID retrieveCarDetailsWithUID = new RetrieveCarDetailsWithUID();
            retrieveCarDetailsWithUID.new  CreateNewProduct(getApplicationContext()).execute(carID);
        }


    }

}
