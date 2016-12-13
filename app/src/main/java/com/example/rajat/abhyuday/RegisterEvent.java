package com.example.rajat.abhyuday;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by rajat on 22-11-2016.
 */

public class RegisterEvent extends AppCompatActivity{
    private static final String TAG = "SignupActivity";
    EditText _nameText;
    EditText _eventname;
    EditText _emailText;
    EditText _mobileText;
    Button _signupButton;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        //ButterKnife.bind(this);

        _nameText=(EditText)findViewById(R.id.input_name);
        _eventname=(EditText)findViewById(R.id.input_address);
        _emailText=(EditText)findViewById(R.id.input_email);
        _mobileText=(EditText)findViewById(R.id.input_mobile);
        _signupButton=(Button)findViewById(R.id.btn_signup);

        _signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signup();
            }
        });


    }

    public void signup() {
        Log.d(TAG, "Signup");

        if (!validate()) {
            onSignupFailed();
            return;
        }

        _signupButton.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(RegisterEvent.this,
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Registering for Event...");
        progressDialog.show();

        String name = _nameText.getText().toString();
        String address = _eventname.getText().toString();
        String email = _emailText.getText().toString();
        String mobile = _mobileText.getText().toString();

        new EventActivityDB(this).execute(name, email, mobile, address);
        // TODO: Implement your own signup logic here.

        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        // On complete call either onSignupSuccess or onSignupFailed
                        // depending on success
                        onSignupSuccess();
                        // onSignupFailed();
                        progressDialog.dismiss();
                    }
                }, 3000);
    }


    public void onSignupSuccess() {
        _signupButton.setEnabled(true);
        setResult(RESULT_OK, null);

        finish();
    }

    public void onSignupFailed() {
        Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show();

        _signupButton.setEnabled(true);
    }

    public boolean validate() {
        boolean valid = true;

        String name = _nameText.getText().toString();
        String address = _eventname.getText().toString();
        String email = _emailText.getText().toString();
        String mobile = _mobileText.getText().toString();


        if (name.isEmpty() || name.length() < 3) {
            _nameText.setError("at least 3 characters");
            valid = false;
        } else {
            _nameText.setError(null);
        }

        if (address.isEmpty()) {
            _eventname.setError("Enter Valid Address");
            valid = false;
        } else {
            _eventname.setError(null);
        }


        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            _emailText.setError("enter a valid email address");
            valid = false;
        } else {
            _emailText.setError(null);
        }

        if (mobile.isEmpty() || mobile.length()!=10) {
            _mobileText.setError("Enter Valid Mobile Number");
            valid = false;
        } else {
            _mobileText.setError(null);
        }

        return valid;
    }
}
