package com.example.crem.jobswipe;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

//import com.example.messagingtutorialskeleton.R;
import com.parse.FindCallback;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class ListUsersActivity extends Activity {

    String currentUserId;
    ArrayList names;
    ListView usersListView;
    ArrayAdapter namesArrayAdapter;
    private ProgressDialog progressDialog;
    private BroadcastReceiver receiver;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        currentUserId = ParseUser.getCurrentUser().getObjectId();
        names = new ArrayList<String>();
        ParseQuery<ParseUser> query = ParseUser.getQuery();
//don't include yourself
        query.whereNotEqualTo("User", currentUserId);
        /*query.findInBackground(new FindCallback<ParseUser>() {
            public void done(List<ParseUser> userList, com.parse.ParseException e) {
                if (e == null) {
                    for (int i=0; i<userList.size(); i++) {
                        names.add(userList.get(i).getUsername().toString());
                    }
                    usersListView = (ListView)findViewById(R.id.usersListView);
                    namesArrayAdapter =
                            new ArrayAdapter<String>(getApplicationContext(),
                                    R.layout.user_list_item, names);
                    usersListView.setAdapter(namesArrayAdapter);
                    usersListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        //@Override
                        public void onItemClick(AdapterView<?> a, View v, int i, long l) {
                            openConversation(names, i);
                        }
                    });
                } else {
                    Toast.makeText(getApplicationContext(),
                            "Error loading user list",
                            Toast.LENGTH_LONG).show();
                }
            }
        });*/
        setContentView(R.layout.activity_main);
    }

    public void openConversation(ArrayList<String> names, int pos) {
        ParseQuery<ParseUser> query = ParseUser.getQuery();
        query.whereEqualTo("username", names.get(pos));
        query.findInBackground(new FindCallback<ParseUser>() {
            @Override
            public void done(List<ParseUser> parseUsers, com.parse.ParseException e) {
                if (e == null) {
                    progressDialog = new ProgressDialog(ListUsersActivity.this);
                    progressDialog.setTitle("Loading");
                    progressDialog.setMessage("Please wait...");
                    progressDialog.show();
//broadcast receiver to listen for the broadcast
//from MessageService
                    Intent intent = new Intent(getApplicationContext(), MessagingActivity.class);
                    intent.putExtra("RECIPIENT_ID", parseUsers.get(0).getObjectId());
                    startActivity(intent);

                    receiver = new BroadcastReceiver() {
                        @Override
                        public void onReceive(Context context, Intent intent) {
                            Boolean success = intent.getBooleanExtra("success", false);
                            progressDialog.dismiss();
                            //show a toast message if the Sinch
                            //service failed to start
                            if (!success) {
                                Toast.makeText(getApplicationContext(), "Messaging service failed to start", Toast.LENGTH_LONG).show();
                            }
                        }
                    };
                    LocalBroadcastManager.getInstance(ListUsersActivity.this).registerReceiver(receiver, new IntentFilter("com.sinch.messagingtutorial.app.ListUsersActivity"));
                } else {
                    Toast.makeText(getApplicationContext(),
                            "Error finding that user",
                            Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

}
