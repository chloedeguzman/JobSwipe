package com.example.crem.jobswipe;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.os.Bundle;

import android.view.Menu;
import android.view.MenuItem;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class EmployeeCreate extends Activity {

    ImageView NoImage, cam;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employe);

        //private static final int CAM_REQUEST = 1313;
        NoImage = (ImageView) findViewById(R.id.NoImage);
        final Button camBtn = (Button) findViewById(R.id.btnAdd);
        final Button addBtn = (Button) findViewById(R.id.btnAdd);
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                Toast.makeText(getApplicationContext(), "Your picture has been uploaded!", Toast.LENGTH_SHORT).show();
            }
        });

        camBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(getApplicationContext(), "Your picture has been uploaded!", Toast.LENGTH_SHORT).show();
                goToContinueEmployeeCreate();
            }
        });

        NoImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), 1);
            }

        });
    }

    public void onActivityResult(int reqCode, int resCode, Intent data) {
        if(resCode == RESULT_OK) {
            if(reqCode == 1)
                NoImage.setImageURI(data.getData());
        }
    }

    /*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_employe, menu);
        return true;
    }*/

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

    public void goToContinueEmployeeCreate(/*View view*/) {
        setContentView(R.layout.employee_create_view);
    }

    public void startCheckingStuff(View view) {
        Intent intent = new Intent(this,CheckingActivity.class);
        startActivity(intent);
    }
}