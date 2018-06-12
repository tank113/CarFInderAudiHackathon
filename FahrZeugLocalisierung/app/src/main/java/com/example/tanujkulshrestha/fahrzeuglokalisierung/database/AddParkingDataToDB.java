package com.example.tanujkulshrestha.fahrzeuglokalisierung.database;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;

import com.example.tanujkulshrestha.fahrzeuglocalisierung.ParkingDataAddedToDB;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;


public class AddParkingDataToDB extends AppCompatActivity {


    // JSON Node names
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
         * */
        @Override
        public void onPreExecute() {

        }

        /**
         * Creating product
         * */
        public String doInBackground(String... args) {

            System.out.println("parkID");
            System.out.println(args[0]);
            String parking_id = args[0];
            String car_id = args[1];
            Date date = new Date();
            SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd+HH:mm:ss");
            String dateTime = ft.format(date);
            System.out.println("date");
            System.out.println(dateTime);
            String url_create_product = "http://10.180.87.63:8888/insert.php?timestamp="+dateTime+"&car_id="+car_id+"&parkingslot_id="+parking_id;

            try {
                URL url = new URL(url_create_product);
                HttpClient client = new DefaultHttpClient();
                HttpGet request = new HttpGet();
                request.setURI(new URI(url_create_product));
                HttpResponse response = client.execute(request);
                BufferedReader in = new BufferedReader(new
                        InputStreamReader(response.getEntity().getContent()));

                StringBuffer sb = new StringBuffer("");
                String line="";

                while ((line = in.readLine()) != null) {
                    sb.append(line);
                    break;
                }

                in.close();
                System.out.println("return");
                System.out.println(sb.toString());
                return sb.toString();
            } catch (MalformedURLException e1) {
                e1.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }


            return null;
        }


        /**
         * After completing background task Dismiss the progress dialog
         * **/
        public void onPostExecute(String file_url) {
            // dismiss the dialog once done
            if (file_url.contains("Success")){
                Intent intent = new Intent(context, ParkingDataAddedToDB.class);
                intent.putExtra("Success", file_url);
                context.startActivity(intent);
            }
            else if(file_url.contains("Error")){
                Intent intent = new Intent(context, ParkingDataAddedToDB.class);
                intent.putExtra("Error", file_url);
                context.startActivity(intent);
            }

        }

    }
}