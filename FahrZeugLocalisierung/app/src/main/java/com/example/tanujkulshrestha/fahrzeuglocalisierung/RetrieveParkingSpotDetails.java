package com.example.tanujkulshrestha.fahrzeuglocalisierung;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.tanujkulshrestha.fahrzeuglokalisierung.utils.JSONParser;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URL;

public class RetrieveParkingSpotDetails extends AppCompatActivity {

    String parkIDToBeSearch;
    String carID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrieve_car_data);

    }



    private static final String TAG_SUCCESS = "success";

    private Context context;


    /**
     * Background Async Task to Create new product
     * */
    public class CreateNewProduct extends AsyncTask<String, String, String> {

        Context context;

        public CreateNewProduct(Context context) {
            this.context = context.getApplicationContext();
        }

        /**
         * Before starting background thread Show Progress Dialog
         */
        @Override
        public void onPreExecute() {

        }

        /**
         * Creating product
         */
        public String doInBackground(String... args) {

            //parkIDToBeSearch = args[0];
            carID = args[0];

            System.out.println("carID"+carID);

            String url_create_product = "http://10.180.87.63:8888/retrieve_carsearch.php?car_id="+carID;
            JSONParser jsonParser = new JSONParser();

            try {
                URL url = new URL(url_create_product);
                HttpClient client = new DefaultHttpClient();
                HttpGet request = new HttpGet();
                request.setURI(new URI(url_create_product));
                HttpResponse response = client.execute(request);
                BufferedReader in = new BufferedReader(new
                        InputStreamReader(response.getEntity().getContent()));

                StringBuffer sb = new StringBuffer("");
                String line = "";

                while ((line = in.readLine()) != null) {
                    sb.append(line);
                    break;
                }
                String finalString = sb.toString();
                //String carid = (String) jsonObject.get("car_id");
                in.close();
                System.out.println("return");
                System.out.println(sb.toString());
                return finalString;

            }catch (Exception e){
                e.printStackTrace();
            }
            return null;
        }

        /**
         * After completing background task Dismiss the progress dialog
         * **/
        public void onPostExecute(String finalString) {

            Object obj = JSONValue.parse(finalString);
            JSONObject jsonObject = (JSONObject)obj;

            String row = (String) ((JSONObject) obj).get("row");
            String slot = (String) ((JSONObject) obj).get("slot");
            String zone_id = (String) ((JSONObject) obj).get("zone_id");
            String building = (String) ((JSONObject) obj).get("building");
            String identifier = (String) ((JSONObject) obj).get("identifier");
            String zone_image = (String) ((JSONObject) obj).get("image");
            double latitude = Double.parseDouble((String) ((JSONObject) obj).get("latitude"));
            double longitude = Double.parseDouble((String) ((JSONObject) obj).get("longitude"));
            System.out.println("directions" + latitude + longitude);
            Intent intent = new Intent(context, ParkingSpotDetails.class);
            intent.putExtra("row", row);
            intent.putExtra("slot", slot);
            intent.putExtra("zone_id", zone_id);
            intent.putExtra("building", building);
            intent.putExtra("identifier", identifier);
            intent.putExtra("zone_image", zone_image);
            intent.putExtra("latitude", latitude);
            intent.putExtra("longitude", longitude);
            context.startActivity(intent);

            // dismiss the dialog once done


        }

    }


}
