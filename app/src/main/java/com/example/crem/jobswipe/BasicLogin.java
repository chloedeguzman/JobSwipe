package com.example.crem.jobswipe;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;

import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.text.ParseException;
import java.util.ArrayList;


public class BasicLogin extends ActionBarActivity {

    private String email;
    private String password;

    private ArrayList<Activity> hack = new ArrayList<Activity>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_basic_login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void attemptLogin() {
        hack.add(this);
        Parse.initialize(this, "PLCqUOBsnu6JD8M8N8wsnASWKKFhmmSRHklTDL0R",
                "1r1tBNa7QU2bRiOQF5AmYO6Uhmj2MSZDZWGWwNwc");
        email = ((AutoCompleteTextView) findViewById(R.id.email)).getText().toString();
        password = ((EditText) findViewById(R.id.password)).getText().toString();
        try {
            ParseUser.logIn(email, password);
        } catch (com.parse.ParseException e) {
            e.printStackTrace();
        }
        if (ParseUser.getCurrentUser() != null) {
            setContentView(R.layout.splash_view);
        }

        ParseQuery<ParseUser> query = ParseUser.getQuery();
        query.whereEqualTo("email", email);
        ParseUser userWithEmail = new ParseUser();
        try {
            userWithEmail = query.getFirst();
        }
        catch (com.parse.ParseException e) {
            e.printStackTrace();
        }
        //hack.add(new ParseUser());
        /*try {
            ParseUser.logIn(hack.get(0).getUsername(), password);
        }
        catch (com.parse.ParseException e) {
            e.printStackTrace();
        }*/
    }
}
