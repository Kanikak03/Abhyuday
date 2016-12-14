package com.example.rajat.abhyuday;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URL;

//import butterknife.ButterKnife;
//import butterknife.Bind;

public class SignupActivity extends AppCompatActivity {
    private static final String TAG = "SignupActivity";

    EditText _nameText;
    EditText _addressText;
    EditText _emailText;
    EditText _mobileText;
    EditText _passwordText;
    EditText _reEnterPasswordText;
    Button _signupButton;
     TextView _loginLink;

    String name;

    String address;
    String email;

    String mobile;

    String password;

    String reEnterPassword;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        //ButterKnife.bind(this);

        _nameText=(EditText)findViewById(R.id.input_name);
        _addressText=(EditText)findViewById(R.id.input_address);
        _emailText=(EditText)findViewById(R.id.input_email);
        _mobileText=(EditText)findViewById(R.id.input_mobile);
        _passwordText=(EditText)findViewById(R.id.input_password);
        _reEnterPasswordText=(EditText)findViewById(R.id.input_reEnterPassword);
        _signupButton=(Button)findViewById(R.id.btn_signup);
        _loginLink=(TextView)findViewById(R.id.link_login);




        _signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signup();
            }
        });

        _loginLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Finish the registration screen and return to the Login activity
                Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(intent);
                finish();
                overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
            }
        });


        name = _nameText.getText().toString();
        address = _addressText.getText().toString();
        email = _emailText.getText().toString();
        mobile = _mobileText.getText().toString();
        password = _passwordText.getText().toString();


    }

    //register task
    private class RegisterTask extends AsyncTask<String,Void,String> {
        final ProgressDialog progressDialog = new ProgressDialog(SignupActivity.this,
                R.style.AppTheme_Dark_Dialog);
        @Override
        protected void onPreExecute() {
            super.onPreExecute();


            progressDialog.setIndeterminate(true);
            progressDialog.setMessage("Creating Account...");
            progressDialog.show();
        }

        @Override
        protected void onPostExecute(final String s) {
            super.onPostExecute(s);
            new android.os.Handler().postDelayed(
                    new Runnable() {
                        public void run() {
                            // On complete call either onLoginSuccess or onLoginFailed
                            progressDialog.dismiss();
                        }
                    }, 3000);
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected String doInBackground(final String... arg0) {
            final String name = (String) arg0[0];
            final String email = (String) arg0[1];
            final String mobile= (String) arg0[2];
            final String address = (String) arg0[3];
            final String password =(String) arg0[4];

            runOnUiThread(new Runnable() {
                @Override
                public void run() {

                    try{

                        String result="";
                        String link = IPAddress.IP+"RegisterInsert.php?name=" + name + "&password=" + password + "&email=" + email +"&mobile="
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
                        result=sb.toString();
                        if (result.isEmpty()) {

                            onSignupFailed();
                        }
                        else {
                            onSignupSuccess();
                        }
                    }
                    catch (Exception e) {
                        System.out.print(("Exception: " + e.getMessage()));
                    }
                }
            });
            return null;
        }

    }


    public void signup() {
        Log.d(TAG, "Signup");

        if (!validate()) {
            onSignupFailed();
            return;
        }
        else
        {
            new RegisterTask().execute(name,email,mobile,address,password);

        }

        _signupButton.setEnabled(false);


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
        String address = _addressText.getText().toString();
        String email = _emailText.getText().toString();
        String mobile = _mobileText.getText().toString();
        String password = _passwordText.getText().toString();
        String reEnterPassword = _reEnterPasswordText.getText().toString();

        if (name.isEmpty() || name.length() < 3) {
            _nameText.setError("at least 3 characters");
            valid = false;
        } else {
            _nameText.setError(null);
        }

        if (address.isEmpty()) {
            _addressText.setError("Enter Valid Address");
            valid = false;
        } else {
            _addressText.setError(null);
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

        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
            _passwordText.setError("between 4 and 10 alphanumeric characters");
            valid = false;
        } else {
            _passwordText.setError(null);
        }

        if (reEnterPassword.isEmpty() || reEnterPassword.length() < 4 || reEnterPassword.length() > 10 || !(reEnterPassword.equals(password))) {
            _reEnterPasswordText.setError("Password Do not match");
            valid = false;
        } else {
            _reEnterPasswordText.setError(null);
        }

        return valid;
    }
}