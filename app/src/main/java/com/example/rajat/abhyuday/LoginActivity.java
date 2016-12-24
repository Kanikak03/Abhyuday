package com.example.rajat.abhyuday;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URL;
import java.util.List;


public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "LoginActivity";
    private static final int REQUEST_SIGNUP = 0;

    Context context;

  EditText _emailText;
     EditText _passwordText;
    Button _loginButton;
     TextView _signupLink;
    TextView _link_guest;

    HttpPost httppost;
    StringBuffer buffer;
    HttpResponse response;
    HttpClient httpclient;
    List<NameValuePair> nameValuePairs;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //ButterKnife.bind(this);
        _emailText=(EditText)findViewById(R.id.input_email);
        _passwordText=(EditText)findViewById(R.id.input_password);
        _loginButton=(Button)findViewById(R.id.btn_login);
        _signupLink=(TextView)findViewById(R.id.link_signup);
        _link_guest=(TextView)findViewById(R.id.link_guest);
        _loginButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                login();
            }
        });

        _signupLink.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // Start the Signup activity
                Intent intent = new Intent(getApplicationContext(), SignupActivity.class);
                startActivityForResult(intent, REQUEST_SIGNUP);
                finish();
                overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
            }
        });
        _link_guest.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Home.class);
                startActivity(intent);
                finish();
                overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
            }
        });


    }

    public void login() {
        Log.d(TAG, "Login");

        if (!validate()) {
            onLoginFailed();
            return;
        }

        _loginButton.setEnabled(false);

        new loginTask().execute(_emailText.getText().toString(),_passwordText.getText().toString());

    }
    private class loginTask extends AsyncTask<String,Void,String> {


        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

                        // On complete call either onLoginSuccess or onLoginFailed
                          if (s.equals("failure")) {

                            onLoginFailed();
                        }
                        else {
                            System.out.print(s);
                            onLoginSuccess();

                        }

                        }
        @Override
        protected String doInBackground(String... params) {


                    String email = params[0];
                    String password = params[1];

                    if (email.equals("admin@admin.com") && password.equals("admin")) {
                       onLoginSuccess();
                    } else {
                        // to check for other users
                        try {
                            String link = IPAddress.IP + "login_user.php?username=" + email + "&password=" + password;
                            System.out.println(link + "");

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
                            String result=sb.toString();
                            System.out.print(result);
                            return result;
                        }
                        catch (Exception e) {
                            return new String("Exception: " + e.getMessage());
                        }
                    }

            return null;

        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_SIGNUP) {
            if (resultCode == RESULT_OK) {

                // TODO: Implement successful signup logic here
                // By default we just finish the Activity and log them in automatically
                this.finish();
            }
        }
    }

    @Override
    public void onBackPressed() {
        // Disable going back to the MainActivity
        moveTaskToBack(true);
    }

    public void onLoginSuccess() {
        _loginButton.setEnabled(true);
        //Toast.makeText(getApplicationContext(), _emailText.toString(), Toast.LENGTH_LONG).show();
        Intent intent = new Intent(getApplicationContext(), EventAdd_Admin.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        //finish();
        overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
        //finish();

    }

    public void onLoginFailed() {
        _loginButton.setEnabled(true);
        Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show();
        //finish();
    }

    public boolean validate() {
        boolean valid = true;

        String email = _emailText.getText().toString();
        String password = _passwordText.getText().toString();

        if (email.isEmpty()) {
           // || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches() - validation not required for now
            _emailText.setError("enter a valid email address");
            valid = false;
        } else {
            _emailText.setError(null);
        }

        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
            _passwordText.setError("between 4 and 10 alphanumeric characters");
            valid = false;
        } else {
            _passwordText.setError(null);
        }

        return valid;
    }
}
