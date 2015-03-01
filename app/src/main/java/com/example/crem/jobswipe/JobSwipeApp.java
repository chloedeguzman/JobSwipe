package com.example.crem.jobswipe;

import android.app.Application;

import com.parse.Parse;

/**
 * Created by eric on 2/28/15.
 */
public class JobSwipeApp extends Application {

    @Override
    public void onCreate() {

        // Enable Local Datastore.
        Parse.enableLocalDatastore(this);
        Parse.initialize(this, "PLCqUOBsnu6JD8M8N8wsnASWKKFhmmSRHklTDL0R", "1r1tBNa7QU2bRiOQF5AmYO6Uhmj2MSZDZWGWwNwc");
    }
}
