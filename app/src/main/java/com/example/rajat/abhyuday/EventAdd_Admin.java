package com.example.rajat.abhyuday;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by rajat on 24-12-2016.
 */

public class EventAdd_Admin extends AppCompatActivity {


    EditText get_eventname;
    EditText _eventdesc;
    EditText _cprize1;
    EditText _cprize2;
    Button _addevent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_event);
        get_eventname = (EditText) findViewById(R.id.event_name);
        _eventdesc = (EditText) findViewById(R.id.event_desc);
        _cprize1 = (EditText) findViewById(R.id.cPrice1);
        _cprize2 = (EditText) findViewById(R.id.cPrice2);
        _addevent = (Button) findViewById(R.id.add_event);

        _addevent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addevent();
            }
        });

    }
    public void addevent() {


        if (!validate()) {
            Toast.makeText(this, "Details not Valid", Toast.LENGTH_SHORT).show();
            return;
        }

        _addevent.setEnabled(false);


        String name = get_eventname.getText().toString();
        String desc = _eventdesc.getText().toString();
        String cp1 = _cprize2.getText().toString();
        String cp2 = _cprize2.getText().toString();

        new Event_add_Admin_DB(this).execute(name, desc, cp1, cp2);
    }

    public boolean validate() {
        boolean valid = true;

        String name = get_eventname.getText().toString();
        String desc = _eventdesc.getText().toString();
        String cp1 = _cprize1.getText().toString();
        String cp2 = _cprize1.getText().toString();


        if (name.isEmpty() || name.length() < 3) {
            get_eventname.setError("at least 3 characters");
            valid = false;
        } else {
            get_eventname.setError(null);
        }

        if (desc.isEmpty()) {
            _eventdesc.setError("Enter Valid Event description");
            valid = false;
        } else {
            _eventdesc.setError(null);
        }


        if (cp1.isEmpty()) {
            _cprize1.setError("enter a cash proze");
            valid = false;
        } else {
            _cprize1.setError(null);
        }

        if (cp2.isEmpty()) {
            _cprize2.setError("Enter Valid cash prize");
            valid = false;
        } else {
            _cprize2.setError(null);
        }

        return valid;
    }
}
