package com.example.crem.jobswipe;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

//import com.example.messagingtutorialskeleton.R;
import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SignUpCallback;


public class LoginActivity2 extends Activity {
    //@Override
    Button loginButton;
    Button signUpButton;
    EditText usernameField;
    EditText passwordField;
    String username;
    String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Parse.initialize(this, "PLCqUOBsnu6JD8M8N8wsnASWKKFhmmSRHklTDL0R", "1r1tBNa7QU2bRiOQF5AmYO6Uhmj2MSZDZWGWwNwc");
        setContentView(R.layout.activity_login2);
        ParseObject testObject = new ParseObject("TestObject");
        testObject.put("foo", "bar");
        testObject.saveInBackground();

        loginButton = (Button) findViewById(R.id.loginButton);
        signUpButton = (Button) findViewById(R.id.signupButton);
        usernameField = (EditText) findViewById(R.id.loginUsername);
        passwordField = (EditText) findViewById(R.id.loginPassword);

        loginButton.setOnClickListener(new View.OnClickListener() {
            //@Override
            public void onClick(View view) {
                username = usernameField.getText().toString();
                password = passwordField.getText().toString();
                Log.d("Login: ", "In Login Click");
                ParseUser.logInInBackground(username, password, new LogInCallback() {
                    public void done(ParseUser user, com.parse.ParseException e) {
                        if (user != null) {
                            final Intent intent = new Intent(getApplicationContext(), ListUsersActivity.class);
                            final Intent serviceIntent = new Intent(getApplicationContext(), MessageService.class);
                            startService(serviceIntent);
                            startActivity(intent);
                        } else {
                            Toast.makeText(getApplicationContext(),
                                    "There was an error logging in.",
                                    Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });

        signUpButton.setOnClickListener(new View.OnClickListener() {
            //@Override
            public void onClick(View view) {
                username = usernameField.getText().toString();
                password = passwordField.getText().toString();
                ParseUser user = new ParseUser();
                user.setUsername(username);
                user.setPassword(password);
                user.signUpInBackground(new SignUpCallback() {
                    public void done(com.parse.ParseException e) {
                        if (e == null) {
                            final Intent intent = new Intent(getApplicationContext(), ListUsersActivity.class);
                            final Intent serviceIntent = new Intent(getApplicationContext(), MessageService.class);
                            startService(serviceIntent);
                            startActivity(intent);
                        } else {
                            Toast.makeText(getApplicationContext(),
                                    "There was an error signing up."
                                    , Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });
    }
    @Override
    public void onDestroy() {
        stopService(new Intent(this, MessageService.class));
        super.onDestroy();
    }

}
