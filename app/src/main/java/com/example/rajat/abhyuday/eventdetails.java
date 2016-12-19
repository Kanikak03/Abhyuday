package com.example.rajat.abhyuday;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class eventdetails extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eventdetails);
        Bundle bundle;
        bundle= getIntent().getExtras();
        TextView textView;
        textView=(TextView)findViewById(R.id.textview);
        textView.setText(bundle.getString("ename"));
        ImageView img;
        img=(ImageView)findViewById(R.id.thumbnail);
        img.setImageResource(bundle.getInt("img"));

        TextView desc;
        desc=(TextView)findViewById(R.id.desc);
        desc.setText(bundle.getString("desc"));

        TextView rules;
        rules=(TextView)findViewById(R.id.link_login);
        rules.setText(bundle.getString("rules"));

        Button reg;
        reg=(Button)findViewById(R.id.btn_reg);
        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mHome = new Intent(eventdetails.this, RegisterEvent.class);
                eventdetails.this.startActivity(mHome);
                eventdetails.this.finish();
            }
        });

    }
}
