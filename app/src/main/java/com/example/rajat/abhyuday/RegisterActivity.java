package com.example.rajat.abhyuday;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URL;

/**
 * Created by rajat on 08-11-2016.
 */
public class RegisterActivity extends AsyncTask<String,Void,String> {

    Context context;
    public RegisterActivity(Context context) {

        this.context=context;
    }


    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);

        if (s.isEmpty()) {

            Toast.makeText(context,"Could  not insert the record",Toast.LENGTH_LONG).show();
        }
        else {
            Toast.makeText(context,"Record Inserted. Status --> "+s,Toast.LENGTH_LONG).show();
        }

    }

    @Override
    protected String doInBackground(String... arg0) {
        try {
            String name = (String) arg0[0];
            String email = (String) arg0[1];
            String mobile= (String) arg0[2];
            String address = (String) arg0[3];
            String password =(String) arg0[4];

            String link = "http://192.168.1.7/RegisterInsert.php?name=" + name + "&password=" + password + "&email=" + email +"&mobile="
                    + mobile + "&address=" + address ;
            System.out.println(link+"");

            URL url = new URL(link);
            HttpClient client = new DefaultHttpClient();
            HttpGet request = new HttpGet();
            request.setURI(new URI(link));
            HttpResponse response = client.execute(request);
            BufferedReader in = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

            StringBuffer sb = new StringBuffer("");
            String line = "";

            while ((line = in.readLine()) != null) {
                sb.append(line);
                Log.i("INFO", "Line ---> " + line);
                break;
            }
            in.close();
            return sb.toString();
        }
        catch (Exception e) {
            return new String("Exception: " + e.getMessage());
        }
    }
}
