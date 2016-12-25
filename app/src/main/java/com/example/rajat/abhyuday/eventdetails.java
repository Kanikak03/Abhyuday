package com.example.rajat.abhyuday;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class eventdetails extends AppCompatActivity {
    TextView textView,desc,rules;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eventdetails);
        Bundle bundle;
        bundle= getIntent().getExtras();

        textView=(TextView)findViewById(R.id.textview);
        textView.setText(bundle.getString("ename"));
        ImageView img;
        img=(ImageView)findViewById(R.id.thumbnail);
        img.setImageResource(bundle.getInt("img"));


        desc=(TextView)findViewById(R.id.desc);
        desc.setText(bundle.getString("desc"));


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

        ImageButton share=(ImageButton)findViewById(R.id.imageButton_share);
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                String shareBody = textView.getText().toString()+"   "+desc.getText().toString()+"   "+rules.getText().toString()+ "  ";
                sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Abhyuday");
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
                startActivity(Intent.createChooser(sharingIntent, "Share via"));

            }
        });

    }
}
