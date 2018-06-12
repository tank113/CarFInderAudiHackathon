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

public class RetrieveCarDetailsWithUID extends AppCompatActivity {

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

            String carIDToBeSearch = args[0];

            String url_create_product = "http://10.180.87.63:8888/retrieve_car.php?car_id="+carIDToBeSearch;
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

            String carID = (String) ((JSONObject) obj).get("car_id");
            String model = (String) ((JSONObject) obj).get("model");
            String colour = (String) ((JSONObject) obj).get("colour");
            String factory = (String) ((JSONObject) obj).get("factory");
            String image = (String) ((JSONObject) obj).get("image");
            Intent intent = new Intent(context, CarConfirmationActivity.class);
            intent.putExtra("carID", carID);
            intent.putExtra("model", model);
            intent.putExtra("colour", colour);
            intent.putExtra("factory", factory);
            intent.putExtra("image", image);

            context.startActivity(intent);

            // dismiss the dialog once done


        }

    }


}
