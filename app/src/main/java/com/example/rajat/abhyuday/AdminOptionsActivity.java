package com.example.rajat.abhyuday;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by rajat on 25-12-2016.
 */

public class AdminOptionsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_options);
        Button add=(Button)findViewById(R.id.btn_add);
        Button view=(Button)findViewById(R.id.btn_view);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mHome = new Intent(AdminOptionsActivity.this, EventAdd_Admin.class);
                AdminOptionsActivity.this.startActivity(mHome);
                AdminOptionsActivity.this.finish();
            }
        });
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mHome = new Intent(AdminOptionsActivity.this, ViewUser.class);
                AdminOptionsActivity.this.startActivity(mHome);
                AdminOptionsActivity.this.finish();
            }
        });

    }
}
