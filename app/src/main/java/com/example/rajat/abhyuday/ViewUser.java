package com.example.rajat.abhyuday;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by rajat on 25-12-2016.
 */

public class ViewUser extends AppCompatActivity{

    static String name,event;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_user);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        connect_to_db();
    }
    public void connect_to_db() {
        String data;
        List<String> r = new ArrayList<String>();
       // ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, r);
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_2, android.R.id.text1, r) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                TextView text1 = (TextView) view.findViewById(android.R.id.text1);
                TextView text2 = (TextView) view.findViewById(android.R.id.text2);

                text1.setText(ViewUser.name);
                text2.setText(ViewUser.event);
                return view;
            }
        };
        ListView lv = (ListView) findViewById(android.R.id.list);
        try {


            DefaultHttpClient client = new DefaultHttpClient();
            HttpGet request = new HttpGet(IPAddress.IP + "eventlist.php");
            HttpResponse response = client.execute(request);
            HttpEntity entity = response.getEntity();
            data = EntityUtils.toString(entity);
            Log.e("STRING", data);
            try {

                JSONArray json = new JSONArray(data);
                for (int i = 0; i < json.length(); i++) {
                    JSONObject obj = json.getJSONObject(i);
                    name=obj.getString("name");
                    event = obj.getString("event");
                    Log.e("STRING", name);
                    r.add(name);
                    r.add(event);
                    lv.setAdapter(adapter);

                }

            } catch (JSONException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        } catch (ClientProtocolException e) {
            Log.d("HTTPCLIENT", e.getLocalizedMessage());
        } catch (IOException e) {
            Log.d("HTTPCLIENT", e.getLocalizedMessage());
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent mHome = new Intent(ViewUser.this, AdminOptionsActivity.class);
        ViewUser.this.startActivity(mHome);
        ViewUser.this.finish();
    }
}
