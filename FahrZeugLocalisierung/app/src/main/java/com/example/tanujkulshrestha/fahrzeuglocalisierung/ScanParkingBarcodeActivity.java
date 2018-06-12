package com.example.tanujkulshrestha.fahrzeuglocalisierung;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.ViewGroup;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.Result;

import java.util.ArrayList;
import java.util.List;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class ScanParkingBarcodeActivity extends AppCompatActivity implements ZXingScannerView.ResultHandler {
    private ZXingScannerView mScannerView;
    private ArrayList<Integer> mSelectedIndices;
    String car_id;

    @Override
    public void onCreate(Bundle state) {
        super.onCreate(state);
        setContentView(R.layout.activity_scan_barcode);
        ViewGroup contentFrame = (ViewGroup) findViewById(R.id.barcodeScannerContent);
        mScannerView = new ZXingScannerView(this);
        setupFormats();
        contentFrame.addView(mScannerView);

        Bundle extras = getIntent().getExtras();
        if(extras == null) {
            car_id= null;


        } else {
            car_id= extras.getString("carID");

        }
    }

    @Override
    public void onResume() {
        super.onResume();
        mScannerView.setResultHandler(this);
        mScannerView.startCamera();
        mScannerView.resumeCameraPreview(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        mScannerView.stopCamera();

    }

    public void setupFormats() {
        List<BarcodeFormat> formats = new ArrayList<BarcodeFormat>();
        if(mSelectedIndices == null || mSelectedIndices.isEmpty()) {
            mSelectedIndices = new ArrayList<Integer>();
            for(int i = 0; i < ZXingScannerView.ALL_FORMATS.size(); i++) {
                mSelectedIndices.add(i);
            }
        }

        for(int index : mSelectedIndices) {
            formats.add(ZXingScannerView.ALL_FORMATS.get(index));
        }
        if(mScannerView != null) {
            mScannerView.setFormats(formats);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void handleResult(final Result rawResult) {
        String message = "Contents = " + rawResult.getText() + ", Format = " + rawResult.getBarcodeFormat().toString();
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(message)
                .setTitle("Barcode Read");
        Log.v("tag", rawResult.getText()); // Prints scan results
        Log.v("tag2", rawResult.getBarcodeFormat().toString());

        builder.setPositiveButton("Publish", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //ScanParkingBarcodeActivity.this.publishText(rawResult.getText());
                Log.v("tag3", rawResult.getBarcodeFormat().toString());
                mScannerView.resumeCameraPreview(ScanParkingBarcodeActivity.this);
                /*Intent intent = new Intent(getApplicationContext(), DisplayParkingDetails.class);
                intent.putExtra("carID", car_id);
                intent.putExtra("parking_id", rawResult.getText());
                startActivity(intent);*/
                RetrieveParkingData retrieveParkingData = new RetrieveParkingData();
                retrieveParkingData.new  CreateNewProduct(getApplicationContext()).execute(rawResult.getText(), car_id);
                //AddParkingDataToDB addParkingDataToDB = new AddParkingDataToDB();
                //addParkingDataToDB.new  CreateNewProduct().execute(rawResult.getText());

            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mScannerView.resumeCameraPreview(ScanParkingBarcodeActivity.this);
            }
        });

        builder.create().show();
    }


}

